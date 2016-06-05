package com.wuchao.test;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.wuchao.Entity.Documents;
import com.wuchao.Entity.User;
import com.wuchao.service.DocumentsService;
import com.wuchao.service.DocumentsServiceImpl;
import com.wuchao.service.UserServiceImpl;

public class WorkTest {
//	@Resource
//	private UserServiceImpl userService;
//	@Resource
//	private DocumentsService documentsService;
	@Test
	public void test_1(){ 
//		BeanFactory factory = new ClassPathXmlApplicationContext("App*.xml");
//		userService = (UserServiceImpl) factory.getBean("userService");
//		documentsService = (DocumentsServiceImpl) factory.getBean("documentsService");
//		User user = userService.findUserByName("Tom");
//		Documents doc = new Documents();
//		doc.setName("doc1");
//		doc.setUser(user);
//		documentsService.addDocuments(doc);
		String name = "de3nCXHh_Apache_Commons_Logging_1.2_API.html";
		String names[] = name.split(".");
		for(int i = 0;i<names.length;i++){
			System.out.println(names[i]);
		}
	
	
	}

}
