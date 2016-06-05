package com.wuchao.dao;



import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.wuchao.Entity.Blog;
import com.wuchao.Entity.BlogClasses;
import com.wuchao.Entity.BlogContent;
import com.wuchao.Entity.CommentOfBlog;
import com.wuchao.Entity.CommentOfDoc;
import com.wuchao.Entity.Documents;
import com.wuchao.Entity.FileClasses;
import com.wuchao.Entity.User;


public class CommentOfDocDaoImpl implements CommentOfDocDao{
	
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
	public void addCommentOfDoc(CommentOfDoc commentOfDoc) {
		// TODO Auto-generated method stub
		getSession().save(commentOfDoc);
	}

	@Override
	public List<CommentOfDoc> getCommentOfDocList(String dockey) {
		// TODO Auto-generated method stub
		return (List<CommentOfDoc>) getSession().createQuery("from CommentOfDoc commentOfDoc where commentOfDoc.dockey = ?").setParameter(0, dockey).list();
	}

	@Override
	public void removeCommentOfDoc(String dockey) {
		// TODO Auto-generated method stub
		List<CommentOfDoc> commentOfDocList = (List<CommentOfDoc>) getSession().createQuery("from CommentOfDoc commentOfDoc where commentOfDoc.dockey = ?").setParameter(0, dockey).list();
		for(int i=0;i<commentOfDocList.size();i++){
			getSession().delete(commentOfDocList.get(i));
		}
	}

	
}
