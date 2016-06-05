package com.wuchao.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wuchao.Entity.Documents;
import com.wuchao.Entity.User;

public interface FileUpLoadService {
	public void upLoad(CommonsMultipartFile file,String className, User user);
	public void delete(Documents documents,User user);
	public String uploadAttachment(CommonsMultipartFile attachment, User userValid);
}
