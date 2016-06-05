package com.wuchao.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wuchao.Entity.Documents;
import com.wuchao.Entity.FileClasses;
import com.wuchao.Entity.User;
import com.wuchao.dao.UserDao;


public class UserServiceImpl implements UserService {
	private UserDao userDao;
	
	
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	// notice the absence of transaction demarcation code in this method
    // Spring's declarative transaction infrastructure will be demarcating
    // transactions on your behalf 
	@Transactional
	@Override 
	public User findUserByName(String name) {
		// TODO Auto-generated method stub
		return userDao.findUserByName(name);
	}
	@Transactional
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
	}
	@Transactional
	@Override
	public boolean isExsit(User user) {
		// TODO Auto-generated method stub
		return userDao.isExsit(user);
	}
	@Transactional
	@Override
	public boolean userValid(User user) {
		// TODO Auto-generated method stub
		return userDao.userValid(user);
	}
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDao.updateUser(user);
	}
	
}
