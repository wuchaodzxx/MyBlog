package com.wuchao.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wuchao.Entity.Documents;
import com.wuchao.Entity.User;
import com.wuchao.dao.DocumentsDao;
import com.wuchao.dao.FileUpLoadDao;

public class FileUpLoadServiceImpl implements FileUpLoadService {
	private FileUpLoadDao fileUpLoadDao;
	public FileUpLoadDao getFileUpLoadDao() {
		return fileUpLoadDao;
	}
	public void setFileUpLoadDao(FileUpLoadDao fileUpLoadDao) {
		this.fileUpLoadDao = fileUpLoadDao;
	}
	@Transactional
	@Override
	public void upLoad( CommonsMultipartFile file,String className, User user) {
		// TODO Auto-generated method stub
		fileUpLoadDao.upLoad(file,className, user);
	}
	@Transactional
	@Override
	public void delete(Documents documents,User user) {
		// TODO Auto-generated method stub
		System.out.println("FileUpLoadServiceImpl:删除文件"+documents.getName());
		fileUpLoadDao.delete(documents,user);
	}
	@Transactional
	@Override
	public String uploadAttachment(CommonsMultipartFile attachment, User userValid) {
		// TODO Auto-generated method stub
		return fileUpLoadDao.uploadAttachment(attachment,userValid);
	}

}
