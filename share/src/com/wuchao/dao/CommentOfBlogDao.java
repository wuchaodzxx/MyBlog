package com.wuchao.dao;

import java.util.List;

import com.wuchao.Entity.Blog;
import com.wuchao.Entity.BlogClasses;
import com.wuchao.Entity.BlogContent;
import com.wuchao.Entity.CommentOfBlog;
import com.wuchao.Entity.CommentOfDoc;
import com.wuchao.Entity.User;

public interface CommentOfBlogDao {
	
	public void addCommentOfBlog(CommentOfBlog commentOfblog);
	public void removeCommentOfBlog(String blogkey);
	public List<CommentOfBlog> getCommentOfBlogList(String blogkey);
	
}
