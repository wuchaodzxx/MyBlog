package com.wuchao.service;


import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wuchao.Entity.Blog;
import com.wuchao.Entity.BlogClasses;
import com.wuchao.Entity.BlogContent;
import com.wuchao.Entity.CommentOfDoc;
import com.wuchao.Entity.Documents;
import com.wuchao.Entity.FileClasses;
import com.wuchao.Entity.User;
import com.wuchao.dao.BlogDao;
import com.wuchao.dao.CommentOfDocDao;
import com.wuchao.dao.DocumentsDao;
import com.wuchao.utils.GeneratorId;



public class CommentOfDocServiceImpl implements CommentOfDocService {
	private CommentOfDocDao commentOfDocDao;
	
	public CommentOfDocDao getCommentOfDocDao() {
		return commentOfDocDao;
	}
	public void setCommentOfDocDao(CommentOfDocDao commentOfDocDao) {
		this.commentOfDocDao = commentOfDocDao;
	}
	
	@Transactional
	@Override
	public void addCommentOfDoc(CommentOfDoc commentOfDoc) {
		// TODO Auto-generated method stub
		commentOfDocDao.addCommentOfDoc(commentOfDoc);
	}
	@Transactional
	@Override
	public List<CommentOfDoc> getCommentOfDocList(String dockey) {
		// TODO Auto-generated method stub
		return commentOfDocDao.getCommentOfDocList(dockey);
	}
	@Transactional
	@Override
	public void removeCommentOfDoc(String dockey) {
		// TODO Auto-generated method stub
		commentOfDocDao.removeCommentOfDoc(dockey);
	}
	
	
	
	
}
