package com.wuchao.service;

import com.wuchao.Entity.Documents;
import com.wuchao.Entity.FileClasses;
import com.wuchao.Entity.User;


public interface UserService {

	public User findUserByName(String name);
	public void addUser(User user);
	public boolean isExsit(User user);
	public boolean userValid(User user);//登陆时，验证是否正确

}
