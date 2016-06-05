package com.wuchao.dao;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.wuchao.Entity.FileClasses;
import com.wuchao.Entity.User;



public class UserDaoImpl implements UserDao{
	
	private SessionFactory  mySessionFactory;
	
	public SessionFactory getMySessionFactory() {
		return mySessionFactory;
	}

	public void setMySessionFactory(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}
	public Session getSession(){
		return mySessionFactory.getCurrentSession();
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		//真正实现层
		System.out.println("添加用户到数据库------用户名："+user.getName()+"--用户昵称："+user.getNickName());
		getSession().save(user);
	}

	@Override
	public User findUserByName(String name) {
		// TODO Auto-generated method stub
		//真正实现层
		Session session = getSession();
		User user = (User) session.createQuery("from User user where user.name = ?").setParameter(0, name).list().get(0);
		return user;
	}

	@Override
	public boolean isExsit(User user) {
		// TODO Auto-generated method stub
		Session session = getSession();
		List<User> list=  session.createQuery("from User user where user.name = ?").setParameter(0, user.getName()).list();
		
		return !list.isEmpty();
	}

	
	@Override
	public boolean userValid(User user) {
		// TODO Auto-generated method stub
		Session session = getSession();
		@SuppressWarnings("unchecked")
		List<User> list=  session.createQuery("from User u where u.name= ? and u.password_md5=?")
		        .setParameter(0, user.getName())
		        .setParameter(1, user.getPassword_md5())
		        .list();
		
		return !list.isEmpty();
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
		User user2 = (User) getSession().createQuery("from User u where u.id=?").setParameter(0, user.getId()).list().get(0);
		user2.setLeftStorageSpace(user.getLeftStorageSpace());
		user2.setLeftStorageSpaceSize(user.getLeftStorageSpaceSize());
		user2.setStorageSpace(user.getStorageSpace());
		getSession().update(user2);
	}


	
}
