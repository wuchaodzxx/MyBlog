package com.wuchao.Controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.wuchao.Entity.Documents;
import com.wuchao.Entity.User;
import com.wuchao.service.BlogService;
import com.wuchao.service.DocumentsService;
import com.wuchao.service.FileUpLoadService;
import com.wuchao.service.UserServiceImpl;
import com.wuchao.utils.Utils2Files;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@SessionAttributes(types = User.class)
public class MainController {
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

	public MainController() {// 构造器
		System.out.println("MainController处理器实例化");

	}

	@ModelAttribute("user")
	public User getUser() {
		User user = new User();
		return user;
	}

	// 接受表单请求
	@RequestMapping(value = "/private/main")
	public ModelAndView handleValid(@ModelAttribute("user") User user, HttpSession httpSession) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("private/main");
		return mv;
	}

	@RequestMapping(value = "/private/aa")
	public ModelAndView handleValid() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("private/main");
		return mv;
	}

	@RequestMapping(value = "/private/file")
	public ModelAndView handle1(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("private/file");

		User userValid = (User) req.getSession().getAttribute("userValid");

		// 上传成功后将documents（list）反正session，便于界面显示
		List<Documents> documentsList = documentsService.findDocuments(userValid.getId());
		req.getSession().setAttribute("documentsList", documentsList);
		req.getSession().setAttribute("userValid", userValid);
		return mv;
	}

	@RequestMapping(value = "/private/blog")
	public ModelAndView handle2(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("private/blog");

		return mv;
	}

}
