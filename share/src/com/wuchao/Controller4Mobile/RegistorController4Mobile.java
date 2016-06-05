package com.wuchao.Controller4Mobile;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.wuchao.Entity.BlogClasses;
import com.wuchao.Entity.FileClasses;
import com.wuchao.Entity.User;
import com.wuchao.service.BlogService;
import com.wuchao.service.DocumentsService;
import com.wuchao.service.UserServiceImpl;
import com.wuchao.test.Info;
import com.wuchao.utils.GeneratorId;
import com.wuchao.utils.Utils2Files;

import net.sf.json.JSONObject;

@Controller
public class RegistorController4Mobile {
	@Resource
	private UserServiceImpl userService;
	@Resource
	private DocumentsService documentsService;
	@Resource
	private BlogService blogService;
	@Resource
	Utils2Files utils2Files;
	
	public RegistorController4Mobile(){//构造器
		System.out.println("HelloController4Mobile处理器实例化");
		
	}
	
//	@RequestMapping(value="/registForm")
//	public ModelAndView handleValid(@Valid @ModelAttribute("user") User user,BindingResult bindingResult){
//		ModelAndView mv = new ModelAndView();
//		if(bindingResult.hasErrors()){
//			mv.setViewName("public/regist");
//			return mv;
//		}
//		if(userService.isExsit(user)){
//			mv.addObject("userIsExsit","用户名已存在");
//			mv.setViewName("public/regist");
//			return mv;
//		}
//		
//		user.setLeftStorageSpace(1073741824);//设置用户初试存储空间 1073741824为1G
//		
//		
//		
//		user.setLeftStorageSpaceSize(Utils2Files.getSizeFormat(user.getLeftStorageSpace()));
//		user.setPassword_md5(GeneratorId.md5(user.getPassword()));//密码加密存储
//		user.setPassword("已使用md5加密");//将用户的密码设为其他值，表示不保存用户密码
//		userService.addUser(user);
//		user = userService.findUserByName(user.getName());
//		//设置默认文件分组
//		FileClasses fileClasses = new FileClasses();
//		fileClasses.setClassName("默认分组");
//		fileClasses.setUser(user);
//		documentsService.addFileClasses(fileClasses); 
//		//设置默认博文分组
//		BlogClasses blogClasses = new BlogClasses();
//		blogClasses.setClassName("默认分组");
//		blogClasses.setUser(user);
//		blogService.addBlogClasses(blogClasses);
//		
//		System.out.println("注册成功------昵称："+user.getNickName());
//		mv.setViewName("public/login");
//		return  mv;
//	}
	
	@RequestMapping(value = "/mobile/regist",method=RequestMethod.POST,produces = "application/json;charset=UTF-8") 
	public @ResponseBody ModelAndView handle4Mobile(@RequestBody  Info info){
		System.out.println(info.getName()); 
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", "sucess");
		return new ModelAndView(new MappingJackson2JsonView(),map);

	} 
}
