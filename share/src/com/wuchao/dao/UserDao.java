package com.wuchao.dao;

import com.wuchao.Entity.FileClasses;
import com.wuchao.Entity.User;

public interface UserDao {
	public void addUser(User user);
	public User findUserByName(String name);
	public boolean isExsit(User user);
	public boolean userValid(User user);//登陆时，验证是否正确
	public void updateUser(User user);

}
