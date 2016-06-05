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


public class CommentOfBlogDaoImpl implements CommentOfBlogDao{
	
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
	public void addCommentOfBlog(CommentOfBlog commentOfBlog) {
		// TODO Auto-generated method stub
		getSession().save(commentOfBlog);
	}

	@Override
	public List<CommentOfBlog> getCommentOfBlogList(String blogkey) {
		// TODO Auto-generated method stub
		return (List<CommentOfBlog>) getSession().createQuery("from CommentOfBlog commentOfBlog where commentOfBlog.blogkey = ?").setParameter(0, blogkey).list();
	}

	@Override
	public void removeCommentOfBlog(String blogkey) {
		// TODO Auto-generated method stub
		List<CommentOfBlog> commentOfBlogList = (List<CommentOfBlog>) getSession().createQuery("from CommentOfBlog commentOfBlog where commentOfBlog.blogkey = ?").setParameter(0, blogkey).list();
		
		for(int i=0;i<commentOfBlogList.size();i++){
			getSession().delete(commentOfBlogList.get(i));
		}
	}

	
}
