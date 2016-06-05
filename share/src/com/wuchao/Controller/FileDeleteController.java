package com.wuchao.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wuchao.Entity.Documents;
import com.wuchao.Entity.User;
import com.wuchao.dao.FileUpLoadDao;
import com.wuchao.dao.FileUpLoadDaoImpl;
import com.wuchao.service.CommentOfDocService;
import com.wuchao.service.DocumentsService;
import com.wuchao.service.FileUpLoadService;
import com.wuchao.service.UserServiceImpl;
import com.wuchao.utils.GeneratorId;

import AliyunOSS.OSSDaoImpl;

@Controller
public class FileDeleteController {
	@Resource
	private UserServiceImpl userService;
	@Resource
	private DocumentsService documentsService;
	@Resource
	private FileUpLoadService fileUpLoadService;
	@Resource
	private CommentOfDocService commentOfDocService;
	
	private User userValid=null;
	public FileDeleteController(){//构造器
		System.out.println("FileDeleteController处理器实例化");
	}
	@RequestMapping("/private/deleteDoc")
	public ModelAndView fileDelete(@RequestParam("docId")String docId,HttpServletRequest req){
		ModelAndView mv = new ModelAndView();
		//找到已登陆用户，若无，跳回登陆界面
		userValid = (User) req.getSession().getAttribute("userValid");
		if(userValid==null){
			mv.setViewName("/public/login");
			return mv;
			
		}
		//根据已登陆用户名去数据库找该用户
		User user = userService.findUserByName(userValid.getName());
		//下面为文件删除
		Documents document = documentsService.findDocumentByDocId(docId);
		if(document!=null){
			fileUpLoadService.delete(document,user);
			commentOfDocService.removeCommentOfDoc(document.getDockey());
		}
		userValid = userService.findUserByName(userValid.getName());
		///删除后，重新获取数据，显示到界面
		List<Documents> documentsList = documentsService.findDocuments(user.getId());
		//req.getSession().setAttribute("documentsList", documentsList);
		mv.addObject("documentsList", documentsList);
		//mv.addObject("userValid", userValid);
		req.getSession().setAttribute("userValid", userValid);
		mv.setViewName("/private/file");
		return mv;

	}
	
}
