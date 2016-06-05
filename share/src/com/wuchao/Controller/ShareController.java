package com.wuchao.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.wuchao.Entity.Blog;
import com.wuchao.Entity.CommentOfBlog;
import com.wuchao.Entity.CommentOfDoc;
import com.wuchao.Entity.Documents;
import com.wuchao.Entity.User;
import com.wuchao.service.BlogService;
import com.wuchao.service.CommentOfBlogService;
import com.wuchao.service.CommentOfDocService;
import com.wuchao.service.DocumentsService;
import com.wuchao.service.FileUpLoadService;
import com.wuchao.service.UserServiceImpl;
import com.wuchao.utils.Utils2Files;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@SessionAttributes(types = User.class)
public class ShareController {
	@Resource
	private UserServiceImpl userService;
	@Resource
	private DocumentsService documentsService;
	@Resource
	private FileUpLoadService fileUpLoadService;
	@Resource
	Utils2Files utils2Files;
	@Resource
	private BlogService blogService;
	@Resource
	private CommentOfDocService commentOfDocService;
	@Resource
	private CommentOfBlogService commentOfBlogService;

	public ShareController() {// 构造器
		System.out.println("MainController处理器实例化");

	}

	@ModelAttribute("user")
	public User getUser() {
		User user = new User();
		return user;
	}

	// 请求分享页面
	@RequestMapping(value = "/public/share")
	public ModelAndView handle3(HttpSession httpSession) {
		List<Blog> shareBlogsList = new ArrayList<Blog>();
		List<Documents> shareDocumentsList = new ArrayList<Documents>();
		///////////////// 下面获取分享文件/////////////////////
		shareBlogsList = blogService.getShareBlogs();
		for (int i = 0; i < shareBlogsList.size(); i++) {// 清除blog内容，减少传输数据
			shareBlogsList.get(i).setContent("");
		}
		shareDocumentsList = documentsService.getShareDocuments();

		//////////////////////////////////
		httpSession.setAttribute("shareBlogsList", shareBlogsList);
		httpSession.setAttribute("shareDocumentsList", shareDocumentsList);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("public/share");
		return mv;
	}

	// 用于共享的blog请求
	@RequestMapping(value = "/public/getShareBlog", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String blogReturn2(@RequestParam("blogKey") String blogKey, HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		Blog blog = blogService.getBlogByKey(blogKey);

		JSONArray jsonMembers = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("titleName", blog.getName());
		jsonObject.put("blogContent", blog.getContent());
		jsonObject.put("blogDate", blog.getDate().toLocaleString());
		jsonObject.put("blogClassName", blog.getClassName());
		jsonObject.put("blogkey", blog.getBlogkey());
		jsonObject.put("blogIsShare", blog.getIsShare());

		jsonMembers.add(jsonObject);///////////////////// json第一个元素是当前blog

		return jsonMembers.toString();

	}

	// 用于共享的Doc请求
	@RequestMapping(value = "/public/getShareDocComment", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getShareDocComment(@RequestParam("dockey") String dockey, HttpServletRequest req,
			HttpServletResponse res) throws IOException {

		List<CommentOfDoc> commentOfDocList = commentOfDocService.getCommentOfDocList(dockey);

		JSONArray jsonMembers = new JSONArray();
		for (int i = 0; i < commentOfDocList.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("date", commentOfDocList.get(i).getDate().toString());
			jsonObject.put("user", commentOfDocList.get(i).getUserName());
			jsonObject.put("commentText", commentOfDocList.get(i).getCommentText());
			jsonMembers.add(jsonObject);
		}

		return jsonMembers.toString();

	}

	@RequestMapping(value = "/public/uploadDocCommentText", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String uploadShareDocComment(@RequestParam("content") String content,@RequestParam("dockey") String dockey, HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		
		User user = (User)req.getSession().getAttribute("userValid");
		if(user==null){
			System.out.println("uploadShareDocComment:未登录");
			return "";
			}
		
		content = java.net.URLDecoder.decode(content, "UTF-8");
		
		System.out.println("uploadShareDocComment:content"+content+"===dockey"+dockey);
		CommentOfDoc commentOfDoc = new CommentOfDoc();
		commentOfDoc.setCommentText(content);
		commentOfDoc.setDate(new Date());
		commentOfDoc.setUserId(user.getId());
		commentOfDoc.setUserName(user.getName());
		commentOfDoc.setDockey(dockey);
		
		commentOfDocService.addCommentOfDoc(commentOfDoc);
		documentsService.addDocCommentCount(dockey);
		
		List<CommentOfDoc> commentOfDocList = commentOfDocService.getCommentOfDocList(dockey);
		JSONArray jsonMembers = new JSONArray();
		for (int i = 0; i < commentOfDocList.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("date", commentOfDocList.get(i).getDate().toString());
			jsonObject.put("user", commentOfDocList.get(i).getUserName());
			jsonObject.put("commentText", commentOfDocList.get(i).getCommentText());
			jsonMembers.add(jsonObject);
		}

		return jsonMembers.toString();

	}
	
	@RequestMapping(value = "/public/uploadBlogCommentText", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String uploadShareBlogComment(@RequestParam("content") String content,@RequestParam("blogkey") String blogkey, HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		
		User user = (User)req.getSession().getAttribute("userValid");
		if(user==null){
			System.out.println("uploadShareBlogComment:未登录");
			return "";
			}
		
		content = java.net.URLDecoder.decode(content, "UTF-8");
		
		System.out.println("uploadShareBlogComment:content"+content+"===blogkey"+blogkey);
		CommentOfBlog commentOfBlog = new CommentOfBlog();
		commentOfBlog.setCommentText(content);
		commentOfBlog.setDate(new Date());
		commentOfBlog.setUserId(user.getId());
		commentOfBlog.setUserName(user.getName());
		commentOfBlog.setBlogkey(blogkey);
		
		commentOfBlogService.addCommentOfBlog(commentOfBlog);
		blogService.addBlogCommentCount(blogkey);
		
		List<CommentOfBlog> commentOfBlogList = commentOfBlogService.getCommentOfBlogList(blogkey);
		JSONArray jsonMembers = new JSONArray();
		for (int i = 0; i < commentOfBlogList.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("date", commentOfBlogList.get(i).getDate().toString());
			jsonObject.put("user", commentOfBlogList.get(i).getUserName());
			jsonObject.put("commentText", commentOfBlogList.get(i).getCommentText());
			jsonMembers.add(jsonObject);
		}

		return jsonMembers.toString();

	}
	
	@RequestMapping(value = "/public/getBlogCommentText", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getShareBlogComment(@RequestParam("blogkey") String blogkey, HttpServletRequest req,
			HttpServletResponse res) throws IOException {


		
		List<CommentOfBlog> commentOfBlogList = commentOfBlogService.getCommentOfBlogList(blogkey);
		JSONArray jsonMembers = new JSONArray();
		for (int i = 0; i < commentOfBlogList.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("date", commentOfBlogList.get(i).getDate().toString());
			jsonObject.put("user", commentOfBlogList.get(i).getUserName());
			jsonObject.put("commentText", commentOfBlogList.get(i).getCommentText());
			jsonMembers.add(jsonObject);
		}

		return jsonMembers.toString();

	}
}
