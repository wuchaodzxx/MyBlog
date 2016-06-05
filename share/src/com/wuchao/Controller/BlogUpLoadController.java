package com.wuchao.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wuchao.Entity.Blog;
import com.wuchao.Entity.BlogClasses;
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
public class BlogUpLoadController {
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
	private CommentOfBlogService commentOfBlogService;

	public BlogUpLoadController() {// 构造器
		System.out.println("BlogUpLoadController处理器实例化");
	}
	
	private User userValid=null;
	
	// 以下使用ajax传输的，故无法正常使用视图解析器
	@RequestMapping(value="/private/uploadBlog",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String blogUpload(@RequestParam("content") String content,@RequestParam("titleName") String titleName,@RequestParam("className") String className,HttpServletRequest req,HttpServletResponse res) throws IOException {
		content = java.net.URLDecoder.decode(content, "UTF-8");
		System.out.println("blogUpload------------------content:" + content);
		System.out.println("blogUpload------------------titleName:" + titleName);
		System.out.println("blogUpload------------------contentLength:" + content.length());
		userValid = (User) req.getSession().getAttribute("userValid");
		
		Blog blog = blogService.addBlog(titleName,content,className,userValid);
		req.getSession().setAttribute("currentBlog", blog);
		
		JSONArray jsonMembers = new JSONArray();  
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("titleName", blog.getName());
		jsonObject.put("blogContent", blog.getContent());
		jsonObject.put("blogDate", blog.getDate().toLocaleString());
		jsonObject.put("blogClassName", blog.getClassName());
		jsonObject.put("blogkey", blog.getBlogkey());
		jsonObject.put("blogIsShare", blog.getIsShare());
		
		List<Blog> blogList = blogService.getBlogList(userValid);
		
		jsonMembers.add(jsonObject);/////////////////////json第一个元素是当前blog
		//获取用户的blog所有类别
		List<BlogClasses> blogClasses = blogService.getBlogClassesList(userValid);
		String[] blogArray = new String[blogClasses.size()];
		for(int j=0;j<blogClasses.size();j++){
			blogArray[j] = blogClasses.get(j).getClassName();
		}
		JSONObject jsonObjectClassNames = new JSONObject();
		jsonObjectClassNames.put("classNames", blogArray);
		
		jsonMembers.add(jsonObjectClassNames);/////////////////////////json第二个元素是当前用户的所有className
		
		Blog blog2=null;
		JSONObject jsonObject2=null;
		for(int i=0;i<blogList.size();i++){
			blog2 = blogList.get(i);
			jsonObject2 = new JSONObject();
			jsonObject2.put("titleName", blog2.getName());
			jsonObject2.put("blogContent", blog2.getContent());
			jsonObject2.put("blogDate", blog2.getDate().toLocaleString());
			jsonObject2.put("blogClassName", blog2.getClassName());
			jsonObject2.put("blogKey", blog2.getBlogkey());
			jsonMembers.add(jsonObject2);
		}
		
		
		return jsonMembers.toString();

	}
	
	@RequestMapping(value="/private/uploadReEditBlog",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String reEditBlogUpload(@RequestParam("content") String content,@RequestParam("titleName") String titleName,@RequestParam("className") String className,@RequestParam("currentBlogKey") String currentBlogKey,HttpServletRequest req,HttpServletResponse res) throws IOException {
		content = java.net.URLDecoder.decode(content, "UTF-8");
	
	

		userValid = (User) req.getSession().getAttribute("userValid");
		
		//Blog blog = blogService.addBlog(titleName,content,className,userValid);
		Blog blog = blogService.addReEditBlog(titleName,content,className,currentBlogKey,userValid);
		req.getSession().setAttribute("currentBlog", blog);
		
		JSONArray jsonMembers = new JSONArray();  
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("titleName", blog.getName());
		jsonObject.put("blogContent", blog.getContent());
		jsonObject.put("blogDate", blog.getDate().toLocaleString());
		jsonObject.put("blogClassName", blog.getClassName());
		jsonObject.put("blogkey", blog.getBlogkey());
		jsonObject.put("blogIsShare", blog.getIsShare());
		
		List<Blog> blogList = blogService.getBlogList(userValid);
		
		jsonMembers.add(jsonObject);/////////////////////json第一个元素是当前blog
		//获取用户的blog所有类别
		List<BlogClasses> blogClasses = blogService.getBlogClassesList(userValid);
		String[] blogArray = new String[blogClasses.size()];
		for(int j=0;j<blogClasses.size();j++){
			blogArray[j] = blogClasses.get(j).getClassName();
		}
		JSONObject jsonObjectClassNames = new JSONObject();
		jsonObjectClassNames.put("classNames", blogArray);
		
		jsonMembers.add(jsonObjectClassNames);/////////////////////////json第二个元素是当前用户的所有className
		
		Blog blog2=null;
		JSONObject jsonObject2=null;
		for(int i=0;i<blogList.size();i++){
			blog2 = blogList.get(i);
			jsonObject2 = new JSONObject();
			jsonObject2.put("titleName", blog2.getName());
			jsonObject2.put("blogContent", blog2.getContent());
			jsonObject2.put("blogDate", blog2.getDate().toLocaleString());
			jsonObject2.put("blogClassName", blog2.getClassName());
			jsonObject2.put("blogKey", blog2.getBlogkey());
			
			jsonMembers.add(jsonObject2);
		}
		
		
		return jsonMembers.toString();

	}
	
	@RequestMapping(value="/private/getBlog",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String blogReturn(@RequestParam("blogKey") String blogKey,HttpServletRequest req,HttpServletResponse res) throws IOException {


		
		userValid = (User) req.getSession().getAttribute("userValid");
		
		Blog blog = blogService.getBlogByKey(blogKey);
		req.getSession().setAttribute("currentBlog", blog);
		
		JSONArray jsonMembers = new JSONArray();  
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("titleName", blog.getName());
		jsonObject.put("blogContent", blog.getContent());
		jsonObject.put("blogDate", blog.getDate().toLocaleString());
		jsonObject.put("blogClassName", blog.getClassName());
		jsonObject.put("blogkey", blog.getBlogkey());
		jsonObject.put("blogIsShare", blog.getIsShare());
		
		List<Blog> blogList = blogService.getBlogList(userValid);
		
		jsonMembers.add(jsonObject);/////////////////////json第一个元素是当前blog
		//获取用户的blog所有类别
		List<BlogClasses> blogClasses = blogService.getBlogClassesList(userValid);
		String[] blogArray = new String[blogClasses.size()];
		for(int j=0;j<blogClasses.size();j++){
			blogArray[j] = blogClasses.get(j).getClassName();
		}
		JSONObject jsonObjectClassNames = new JSONObject();
		jsonObjectClassNames.put("classNames", blogArray);
		
		jsonMembers.add(jsonObjectClassNames);/////////////////////////json第二个元素是当前用户的所有className
		
		Blog blog2=null;
		JSONObject jsonObject2=null;
		for(int i=0;i<blogList.size();i++){
			blog2 = blogList.get(i);
			jsonObject2 = new JSONObject();
			jsonObject2.put("titleName", blog2.getName());
			jsonObject2.put("blogContent", blog2.getContent());
			jsonObject2.put("blogDate", blog2.getDate().toLocaleString());
			jsonObject2.put("blogClassName", blog2.getClassName());
			jsonObject2.put("blogKey", blog2.getBlogkey());
			jsonMembers.add(jsonObject2);
		}
		
		
		return jsonMembers.toString();

	}
	
	
	
	@RequestMapping(value="/private/blogInit",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String blogInit(HttpServletRequest req,HttpServletResponse res) throws IOException {


		
		userValid = (User) req.getSession().getAttribute("userValid");
		System.out.print("blogInit:"+userValid.getNickName());
		
		
		JSONArray jsonMembers = new JSONArray();  
		
		List<Blog> blogList = blogService.getBlogList(userValid);
		
		//获取用户的blog所有类别
		List<BlogClasses> blogClasses = blogService.getBlogClassesList(userValid);
		String[] blogArray = new String[blogClasses.size()];
		for(int j=0;j<blogClasses.size();j++){
			blogArray[j] = blogClasses.get(j).getClassName();
		}
		JSONObject jsonObjectClassNames = new JSONObject();
		jsonObjectClassNames.put("classNames", blogArray);
		
		jsonMembers.add(jsonObjectClassNames);
		
		Blog blog2=null;
		JSONObject jsonObject2=null;
		for(int i=0;i<blogList.size();i++){
			blog2 = blogList.get(i);
			jsonObject2 = new JSONObject();
			jsonObject2.put("titleName", blog2.getName());
			jsonObject2.put("blogContent", blog2.getContent());
			jsonObject2.put("blogDate", blog2.getDate().toLocaleString());
			jsonObject2.put("blogClassName", blog2.getClassName());
			jsonObject2.put("blogKey", blog2.getBlogkey());
			jsonMembers.add(jsonObject2);
		}
		
		System.out.println(jsonMembers.toString());
		return jsonMembers.toString();

	}
	@RequestMapping(value = "/private/imageUpload", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String imageUpLoad(@RequestParam("upload") CommonsMultipartFile attachment,
			@RequestParam("CKEditorFuncNum") String callback, HttpServletResponse res, HttpServletRequest req)
			throws IOException {
		// 找到已登陆用户，若无，跳回登陆界面
		User userValid = (User) req.getSession().getAttribute("userValid");
		if (userValid == null) {
			return "/public/login";
		}

		if (attachment.getOriginalFilename().length() < 1
				|| !Utils2Files.typeMatch(attachment.getContentType(), "image")) {
			String returnString = "<script type=\"text/javascript\">" + "window.parent.CKEDITOR.tools.callFunction("
					+ callback + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');" + "</script>";

			return returnString;
		} else {
			// 文件上传并获取地址

			String attachmentUrl = fileUpLoadService.uploadAttachment(attachment, userValid);
			///
			// String url =
			/// "http://documents-oss.oss-cn-qingdao.aliyuncs.com/11/chat2you.club_cert.jpg";
			String returnString = "<script type=\"text/javascript\">" + "window.parent.CKEDITOR.tools.callFunction("
					+ callback + ",'" + attachmentUrl + "','')" + "</script>";
			return returnString;
		}

	}

	//添加文章分类
	@RequestMapping("/private/addBlogClass")
	public void addBlogClass(@RequestParam("className") String className, HttpServletRequest req) {
		if (className.length() >= 1 && className != "null" && className != "undefined" && className != null) {

			userValid = (User) req.getSession().getAttribute("userValid");
			List list = blogService.getClassByClassNameAndUserId(className, userValid.getId());

			if (list.size() == 0) {
				BlogClasses blogClasses = new BlogClasses();
				blogClasses.setClassName(className);
				blogClasses.setUser(userValid);
				blogService.addBlogClasses(blogClasses);
				List<BlogClasses> blogClassesList = blogService.getBlogClasses(userValid.getId());
				req.getSession().setAttribute("blogClassesList", blogClassesList);
			}

		}
	}
	
	@RequestMapping("/private/deleteBlogClass")
	public void deleteBlogClass(@RequestParam("className") String className, HttpServletRequest req) {
		if (className.length() >= 1 && className != "null" && className != "undefined" && className != null) {
			
			userValid = (User) req.getSession().getAttribute("userValid");
			List list = blogService.getClassByClassNameAndUserId(className, userValid.getId());

			if (list.size() > 0) {
				BlogClasses blogClasses = (BlogClasses) list.get(0);
				
				blogService.deleteBlogClasses(blogClasses,userValid);
				
				List<BlogClasses> blogClassesList = blogService.getBlogClasses(userValid.getId());
				req.getSession().setAttribute("blogClassesList", blogClassesList);
			}

		}
	}
	
	
	@RequestMapping(value="/private/deleteBlog",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String deleteBlog(@RequestParam("blogKey") String blogKey,HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		
		userValid = (User) req.getSession().getAttribute("userValid");
		
		blogService.deleteBlog(blogKey);
		commentOfBlogService.removeCommentOfBlog(blogKey);
		
		JSONArray jsonMembers = new JSONArray();  

		List<Blog> blogList = blogService.getBlogList(userValid);
		//获取用户的blog所有类别
		List<BlogClasses> blogClasses = blogService.getBlogClassesList(userValid);
		String[] blogArray = new String[blogClasses.size()];
		for(int j=0;j<blogClasses.size();j++){
			blogArray[j] = blogClasses.get(j).getClassName();
		}
		JSONObject jsonObjectClassNames = new JSONObject();
		jsonObjectClassNames.put("classNames", blogArray);
		
		jsonMembers.add(jsonObjectClassNames);/////////////////////////json第二个元素是当前用户的所有className
		
		Blog blog2=null;
		JSONObject jsonObject2=null;
		for(int i=0;i<blogList.size();i++){
			blog2 = blogList.get(i);
			jsonObject2 = new JSONObject();
			jsonObject2.put("titleName", blog2.getName());
			jsonObject2.put("blogContent", blog2.getContent());
			jsonObject2.put("blogDate", blog2.getDate().toLocaleString());
			jsonObject2.put("blogClassName", blog2.getClassName());
			jsonObject2.put("blogKey", blog2.getBlogkey());
			jsonMembers.add(jsonObject2);
		}
		
		
		return jsonMembers.toString();

	}
	
	@RequestMapping(value="/private/shareBlog",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String shareBlog(@RequestParam("blogkey") String blogkey,HttpServletRequest req){
		blogService.shareBlog(blogkey);
		Blog blog = blogService.getBlogByKey(blogkey);
		req.getSession().setAttribute("currentBlog", blog);
		
		userValid = (User) req.getSession().getAttribute("userValid");
		
		JSONArray jsonMembers = new JSONArray();  
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("titleName", blog.getName());
		jsonObject.put("blogContent", blog.getContent());
		jsonObject.put("blogDate", blog.getDate().toLocaleString());
		jsonObject.put("blogClassName", blog.getClassName());
		jsonObject.put("blogkey", blog.getBlogkey());
		jsonObject.put("blogIsShare", "yes");
		
		jsonMembers.add(jsonObject);/////////////////////json第一个元素是当前blog
		System.out.println("ShareBlog:"+jsonMembers.toString());
		return jsonMembers.toString();
		
	}
	@RequestMapping(value="/private/cancelShareBlog",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String cancelShareBlog(@RequestParam("blogkey") String blogkey,HttpServletRequest req){
		blogService.cancelShareBlog(blogkey);
		Blog blog = blogService.getBlogByKey(blogkey);
		req.getSession().setAttribute("currentBlog", blog);
		
		userValid = (User) req.getSession().getAttribute("userValid");
		
		JSONArray jsonMembers = new JSONArray();  
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("titleName", blog.getName());
		jsonObject.put("blogContent", blog.getContent());
		jsonObject.put("blogDate", blog.getDate().toLocaleString());
		jsonObject.put("blogClassName", blog.getClassName());
		jsonObject.put("blogkey", blog.getBlogkey());
		jsonObject.put("blogIsShare", "no");
		

		
		jsonMembers.add(jsonObject);/////////////////////json第一个元素是当前blog
		System.out.println("cancelShareBlog:"+jsonMembers.toString());
		return jsonMembers.toString();
		
	}
}
