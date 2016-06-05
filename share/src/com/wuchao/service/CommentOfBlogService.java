package com.wuchao.service;


import java.util.List;

import com.wuchao.Entity.Blog;
import com.wuchao.Entity.BlogClasses;
import com.wuchao.Entity.CommentOfBlog;
import com.wuchao.Entity.CommentOfDoc;
import com.wuchao.Entity.FileClasses;
import com.wuchao.Entity.User;


public interface CommentOfBlogService {

	public void addCommentOfBlog(CommentOfBlog commentOfBlog);
	public void removeCommentOfBlog(String blogkey);
	public List<CommentOfBlog> getCommentOfBlogList(String blogkey);

}
