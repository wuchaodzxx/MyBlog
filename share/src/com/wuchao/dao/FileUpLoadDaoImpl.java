package com.wuchao.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wuchao.Entity.Attachment;
import com.wuchao.Entity.Documents;
import com.wuchao.Entity.User;
import com.wuchao.service.AttachmentService;
import com.wuchao.service.DocumentsService;
import com.wuchao.service.UserServiceImpl;
import com.wuchao.utils.GeneratorId;
import com.wuchao.utils.Utils2Files;

import AliyunOSS.OSSDaoImpl;
import AliyunOSS.OSSService;

public class FileUpLoadDaoImpl implements FileUpLoadDao {
	@Resource
	OSSService ossService;
	@Resource
	DocumentsService documentsService;
	@Resource
	AttachmentService attachmentService;
	@Resource
	Utils2Files utils2Files;
	@Resource
	private UserServiceImpl userService;
	
	@Override
	public  void upLoad(CommonsMultipartFile file,String className, User user) {
		// TODO Auto-generated method stub
		String docKey = GeneratorId.getUUID();
		//OSSDaoImpl ossDao = new OSSDaoImpl();
		InputStream is = null;
		try {
			is = file.getInputStream();
			} catch (IOException e) {}
		//查询文件名是否重复，若重复，则追加uuid,并且文件名长度不超过150
		String docName = file.getOriginalFilename().replace(" ", "_");//必须去除空格
		docName = utils2Files.reNameDoc(docName, documentsService, user.getId());//
		
		String md5 = ossService.putFile(is, file.getSize(),docName, file.getContentType(), user.getId()+"");
		System.out.println(file.getContentType());
		String iconName = Utils2Files.setFileIcon(docName); 
		Documents doc = new Documents();//创建文件实例
		doc.setClassName(className);
		doc.setAuthor(user.getName());
		doc.setDate(new Date());
		doc.setDockey(docKey);
		doc.setName(docName);
		doc.setRemarks("");
		doc.setUser(user);
		doc.setMd5(md5);
		doc.setIcon(iconName);
		doc.setSize(Utils2Files.getSizeFormat(file.getSize()));
		doc.setOrignalSize(file.getSize());
		doc.setCommentCount(0);
		//http://documents-oss.oss-cn-qingdao.aliyuncs.com/7/c2346fe7-f399-4b1c-98ec-3b4f89f455b9
		doc.setUrl("http://documents-oss.oss-cn-qingdao.aliyuncs.com/"+user.getId()+"/"+docName);
		documentsService.addDocuments(doc);
		
		user.setStorageSpace(user.getStorageSpace()+file.getSize());
		user.setLeftStorageSpace(user.getLeftStorageSpace()-file.getSize());
		user.setLeftStorageSpaceSize(Utils2Files.getSizeFormat(user.getLeftStorageSpace()));
		userService.updateUser(user);
		

	}
	@Override
	public void delete(Documents documents,User user) {
		// TODO Auto-generated method stub
		String key = documents.getUser().getId()+"/"+documents.getName();
		System.out.println("FileUpLoadDaoImpl:删除文件"+documents.getName());
		ossService.deleteDocument(key);
		documentsService.deleteDocument(documents);

		user.setStorageSpace(user.getStorageSpace()-documents.getOrignalSize());
		user.setLeftStorageSpace(user.getLeftStorageSpace()+documents.getOrignalSize());
		user.setLeftStorageSpaceSize(Utils2Files.getSizeFormat(user.getLeftStorageSpace()));
		
		userService.updateUser(user);
		
	}
	@Override
	public String uploadAttachment(CommonsMultipartFile attachmentFile, User userValid) {
		// TODO Auto-generated method stub
		String uuid = GeneratorId.getUUID();
		String md5=null;
		try {
			md5 = ossService.putFile(attachmentFile.getInputStream(), attachmentFile.getSize(),"attachments/"+uuid, attachmentFile.getContentType(), userValid.getId()+"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Attachment attachment = new Attachment();
		attachment.setAttachmentKey(uuid);
		attachment.setAuthor(userValid.getName());
		attachment.setDate(new Date());
		attachment.setMd5(md5);
		attachment.getName();
		attachment.setSize(Utils2Files.getSizeFormat(attachmentFile.getSize()));
		attachment.setUrl("http://documents-oss.oss-cn-qingdao.aliyuncs.com/"+userValid.getId()+"/"+"attachments/"+uuid);
		attachment.setUser(userValid);
		
		attachmentService.addAttachment(attachment);
		return attachment.getUrl(); 
	}

}
