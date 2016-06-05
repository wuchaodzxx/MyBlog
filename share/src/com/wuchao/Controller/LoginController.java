package com.wuchao.Controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.wuchao.Entity.BlogClasses;
import com.wuchao.Entity.Documents;
import com.wuchao.Entity.FileClasses;
import com.wuchao.Entity.User;
import com.wuchao.service.BlogService;
import com.wuchao.service.DocumentsService;
import com.wuchao.service.UserServiceImpl;
import com.wuchao.utils.GeneratorId;
import com.wuchao.utils.Utils2Files;

@Controller
@SessionAttributes(types = User.class)
public class LoginController {
	@Resource
	private UserServiceImpl userService;
	@Resource
	private DocumentsService documentsService;
	@Resource
	private BlogService blogService;
	private User userValid;
	private static Logger logger = LoggerFactory.getLogger(LoginController.class); 
	
	public LoginController() {// 构造器
		System.out.println("LoginController处理器实例化");

	}

	@ModelAttribute("user")
	public User getUser() {
		User user = new User();
		return user;
	}

	// 接受表单请求
	@RequestMapping(value = "/loginForm")
	public ModelAndView handleValid(@ModelAttribute("user") User user, HttpSession httpSession) {
		ModelAndView mv = new ModelAndView();
		if (user.getName().length() < 1 || user.getPassword().length() < 1) {
			mv.addObject("error", "请输入用户名和密码");
			mv.setViewName("public/login");
			return mv;
		}
		user.setPassword_md5(GeneratorId.md5(user.getPassword()));//密码加密
		if (!userService.userValid(user)) {
			mv.addObject("error", "用户名或密码有误");
			mv.setViewName("/public/login");
			return mv;
		}
		logger.info("this is a log");
		
		
		userValid = userService.findUserByName(user.getName());
		System.out.println("登陆成功：" + userValid.getNickName()+"left:"+userValid.getLeftStorageSpaceSize());
		httpSession.setAttribute("userValid", userValid);
		mv.setViewName("redirect:private/main.do");
		//mv.addObject("userValid", userValid);
		
		// 登陆成功后将documentsList放进session
		List<Documents> documentsList = documentsService.findDocuments(userValid.getId());
		List<FileClasses> fileClassesList = (List<FileClasses>) documentsService.getFileClasses(userValid.getId());
		List<BlogClasses> blogClassesList = (List<BlogClasses>) blogService.getBlogClasses(userValid.getId());
		//Map docWithClasses = Utils2Files.getMapWithDocumentsList(documentsList, fileClassesList);
		System.out.println("登陆时查询文件数目：" + documentsList.size());
		httpSession.setAttribute("documentsList", documentsList);
		httpSession.setAttribute("fileClassesList", fileClassesList);
		httpSession.setAttribute("blogClassesList", blogClassesList);
		//httpSession.setAttribute("docWithClasses", docWithClasses);
		System.out.println("登陆时查询className：" + fileClassesList.size());
		return mv;
	}

	// 接受登陆请求
	@RequestMapping(value = "login")
	public String handle1(@ModelAttribute("user") User user) {
		return "public/login";
	}

	// 接受注销请求
	@RequestMapping(value = "/private/logout")//当映射请求设为logout时会出错？？？
	public String handle2(HttpSession httpSession) {
		httpSession.removeAttribute("userValid");
		
		return "public/login";
	}
}
