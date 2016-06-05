package com.wuchao.dao;

import java.util.List;

import com.wuchao.Entity.Blog;
import com.wuchao.Entity.BlogClasses;
import com.wuchao.Entity.BlogContent;
import com.wuchao.Entity.User;

public interface BlogDao {
	public void addFileClasses(BlogClasses blogClasses);

	public List<BlogClasses> getBlogClasses(int id);

	public List getClassByClassNameAndUserId(String className, int id);

	public void add(Blog blog, BlogContent blogContent, User userValid);

	public List<Blog> getBlogList(User userValid);

	public List<BlogClasses> getBlogClassesList(User userValid);

	public Blog getBlogByKey(String blogKey);

	public Blog getBlogByBlogKey(String blogKey);

	public BlogContent getBlogContentByBlogKey(String blogKey);

	public void deleteBlog(Blog blog, BlogContent blogContent);

	public void deleteBlogClass(BlogClasses blogClasses, User user);

	public void changeBlogWithClass(String newClassName,String oldClassName, User user);

	public Blog addReEditBlog(String titleName, String content, String className, String currentBlogKey,
			User userValid);

	public void shareBlog(String blogkey);

	public void cancelShareBlog(String blogkey);

	public List<Blog> getShareBlogs();

	public void addBlogCommentCount(String blogkey);











}
