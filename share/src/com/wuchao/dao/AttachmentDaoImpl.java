package com.wuchao.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.wuchao.Entity.Attachment;

public class AttachmentDaoImpl implements AttachmentDao {
	
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
	public void addAttachment(Attachment attachment) {
		// TODO Auto-generated method stub
		getSession().save(attachment);
	}

}
