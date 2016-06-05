package com.wuchao.service;


import java.util.List;

import com.wuchao.Entity.Blog;
import com.wuchao.Entity.BlogClasses;
import com.wuchao.Entity.FileClasses;
import com.wuchao.Entity.User;


public interface BlogService {

	public void addBlogClasses(BlogClasses blogClasses);

	public List<BlogClasses> getBlogClasses(int id);

	public List getClassByClassNameAndUserId(String className, int id);

	public Blog addBlog(String titleName, String content, String className,User userValid);

	public List<Blog> getBlogList(User userValid);

	public List<BlogClasses> getBlogClassesList(User userValid);

	public Blog getBlogByKey(String blogKey);

	public void deleteBlog(String blogKey);

	public void deleteBlogClasses(BlogClasses blogClasses,User user);

	public Blog addReEditBlog(String titleName, String content, String className, String currentBlogKey,
			User userValid);

	public void shareBlog(String blogkey);

	public void cancelShareBlog(String blogkey);

	public List<Blog> getShareBlogs();

	public void addBlogCommentCount(String blogkey);

	


}
