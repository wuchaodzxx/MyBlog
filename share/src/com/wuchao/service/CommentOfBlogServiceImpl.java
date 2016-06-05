package com.wuchao.service;


import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wuchao.Entity.Blog;
import com.wuchao.Entity.BlogClasses;
import com.wuchao.Entity.BlogContent;
import com.wuchao.Entity.CommentOfBlog;
import com.wuchao.Entity.CommentOfDoc;
import com.wuchao.Entity.Documents;
import com.wuchao.Entity.FileClasses;
import com.wuchao.Entity.User;
import com.wuchao.dao.BlogDao;
import com.wuchao.dao.CommentOfBlogDao;
import com.wuchao.dao.CommentOfDocDao;
import com.wuchao.dao.DocumentsDao;
import com.wuchao.utils.GeneratorId;



public class CommentOfBlogServiceImpl implements CommentOfBlogService {
	private CommentOfBlogDao commentOfBlogDao;
	
	
	
	public CommentOfBlogDao getCommentOfBlogDao() {
		return commentOfBlogDao;
	}
	public void setCommentOfBlogDao(CommentOfBlogDao commentOfBlogDao) {
		this.commentOfBlogDao = commentOfBlogDao;
	}
	@Transactional
	@Override
	public void addCommentOfBlog(CommentOfBlog commentOfBlog) {
		// TODO Auto-generated method stub
		commentOfBlogDao.addCommentOfBlog(commentOfBlog);
	}
	@Transactional
	@Override
	public List<CommentOfBlog> getCommentOfBlogList(String blogkey) {
		// TODO Auto-generated method stub
		return commentOfBlogDao.getCommentOfBlogList(blogkey);
	}
	@Transactional
	@Override
	public void removeCommentOfBlog(String blogkey) {
		// TODO Auto-generated method stub
		commentOfBlogDao.removeCommentOfBlog(blogkey);
	}
	
	
	
	
}
