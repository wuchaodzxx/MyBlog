package com.wuchao.dao;

import java.util.List;

import com.wuchao.Entity.Blog;
import com.wuchao.Entity.BlogClasses;
import com.wuchao.Entity.BlogContent;
import com.wuchao.Entity.CommentOfDoc;
import com.wuchao.Entity.User;

public interface CommentOfDocDao {
	
	public void addCommentOfDoc(CommentOfDoc commentOfDoc);
	public void removeCommentOfDoc(String dockey);
	public List<CommentOfDoc> getCommentOfDocList(String dockey);
	
}
