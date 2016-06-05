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
import com.wuchao.Entity.FileClasses;
import com.wuchao.Entity.User;
import com.wuchao.dao.FileUpLoadDao;
import com.wuchao.dao.FileUpLoadDaoImpl;
import com.wuchao.service.DocumentsService;
import com.wuchao.service.FileUpLoadService;
import com.wuchao.service.UserServiceImpl;
import com.wuchao.utils.GeneratorId;

import AliyunOSS.OSSDaoImpl;

@Controller
public class FileUpLoadController {
	@Resource
	private UserServiceImpl userService;
	@Resource
	private DocumentsService documentsService;
	@Resource
	private FileUpLoadService fileUpLoadService;
	
	private User userValid=null;
	public FileUpLoadController(){//构造器
		System.out.println("FileUpLoadController处理器实例化");
	}
	@RequestMapping("/private/upload")
	public String fileupload(@RequestParam("file")CommonsMultipartFile file,@RequestParam("className")String className,HttpServletRequest req) throws IOException{
		
		if(file.getOriginalFilename().length()<1){
			
			return "/private/main";
		}
		//找到已登陆用户，若无，跳回登陆界面
		userValid = (User) req.getSession().getAttribute("userValid");
		if(userValid==null){return "/public/login";}
		//根据已登陆用户名去数据库找该用户
		//User user = userService.findUserByName(userValid.getName());
		
		//下面为文件上传
		//先判断用户存储空间是不是满足
		if(userValid.getLeftStorageSpace()<file.getSize()){
			return "/private/file";
		}
		fileUpLoadService.upLoad( file, className,userValid);
		userValid = userService.findUserByName(userValid.getName());
		//上传成功后将documents（list）反正session，便于界面显示
		List<Documents> documentsList = documentsService.findDocuments(userValid.getId());
		req.getSession().setAttribute("documentsList", documentsList);
		req.getSession().setAttribute("userValid", userValid);
		return "/private/file";

	}
	
	//添加文章分类
	@RequestMapping("/private/addClass")
	public  void addClass(@RequestParam("className")String className,HttpServletRequest req){
		if(className.length()>=1&&className!="null"&&className!="undefined"&&className!=null){

			userValid = (User) req.getSession().getAttribute("userValid");
			List list = documentsService.getCLassByClassNameAndUserId(className,userValid.getId());

			if(list.size()==0){
				FileClasses fileClasses = new FileClasses();
				fileClasses.setClassName(className);
				fileClasses.setUser(userValid);
				documentsService.addFileClasses(fileClasses);
				List<FileClasses> fileClassesList = documentsService.getFileClasses(userValid.getId());
				req.getSession().setAttribute("fileClassesList", fileClassesList);
			}
			
				
		}
	}
	
	//添加文章分类
		@RequestMapping("/private/deleteClass")
		public  void deleteClass(@RequestParam("className")String className,HttpServletRequest req){
			
			if(className.length()>=1&&className!="null"&&className!="undefined"&&className!=null){
				
				userValid = (User) req.getSession().getAttribute("userValid");
				documentsService.changeDocumentsClassName("默认分组",className,userValid.getId());
				documentsService.deleteClassName(className,userValid.getId());
				
				List<FileClasses> fileClassesList = documentsService.getFileClasses(userValid.getId());
				userValid = userService.findUserByName(userValid.getName());
				List<Documents> documentsList = documentsService.findDocuments(userValid.getId());
				
				req.getSession().setAttribute("documentsList", documentsList);
				req.getSession().setAttribute("fileClassesList", fileClassesList);
				req.getSession().setAttribute("userValid", userValid);
				
				
					
			}
		}
		
		//更改文章分类
		@RequestMapping("/private/changeDocClass")
		public  void changeDocClass(@RequestParam("className")String className,@RequestParam("docId")String docId,HttpServletRequest req){
			
			if(className.length()>=1&&className!="null"&&className!="undefined"&&className!=null){
				System.out.println("deleteClassName:"+className); 
				userValid = (User) req.getSession().getAttribute("userValid");
				documentsService.changeDocumentsClassName(className,Integer.parseInt(docId));
			
				
				List<FileClasses> fileClassesList = documentsService.getFileClasses(userValid.getId());
				userValid = userService.findUserByName(userValid.getName());
				List<Documents> documentsList = documentsService.findDocuments(userValid.getId());
				
				req.getSession().setAttribute("documentsList", documentsList);
				req.getSession().setAttribute("fileClassesList", fileClassesList);
				req.getSession().setAttribute("userValid", userValid);
				
				
					
			}
		}
		
		@RequestMapping("/private/shareDoc")
		public  void shareDoc(@RequestParam("docId")String docId,HttpServletRequest req){
			documentsService.shareDoc(Integer.parseInt(docId));
			
			userValid = (User) req.getSession().getAttribute("userValid");
			
			//上传成功后将documents（list）反正session，便于界面显示
			List<Documents> documentsList = documentsService.findDocuments(userValid.getId());
			req.getSession().setAttribute("documentsList", documentsList);
			req.getSession().setAttribute("userValid", userValid);
		}
		
		@RequestMapping("/private/cancelShareDoc")
		public  void cancelShareDoc(@RequestParam("docId")String docId,HttpServletRequest req){
			documentsService.cancelShareDoc(Integer.parseInt(docId));
			
			userValid = (User) req.getSession().getAttribute("userValid");
			
			//上传成功后将documents（list）反正session，便于界面显示
			List<Documents> documentsList = documentsService.findDocuments(userValid.getId());
			req.getSession().setAttribute("documentsList", documentsList);
			req.getSession().setAttribute("userValid", userValid);
		}
	
}
