package com.wuchao.service;


import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wuchao.Entity.Blog;
import com.wuchao.Entity.BlogClasses;
import com.wuchao.Entity.BlogContent;
import com.wuchao.Entity.Documents;
import com.wuchao.Entity.FileClasses;
import com.wuchao.Entity.User;
import com.wuchao.dao.BlogDao;
import com.wuchao.dao.DocumentsDao;
import com.wuchao.utils.GeneratorId;



public class BlogServiceImpl implements BlogService {
	private BlogDao blogDao;
	public BlogDao getBlogDao() {
		return blogDao;
	}
	public void setBlogDao(BlogDao blogDao) {
		this.blogDao = blogDao;
	}
	

	@Transactional
	@Override
	public void addBlogClasses(BlogClasses blogClasses) {
		// TODO Auto-generated method stub
		blogDao.addFileClasses(blogClasses);
	}
	@Transactional
	@Override
	public List<BlogClasses> getBlogClasses(int id) {
		// TODO Auto-generated method stub
		return blogDao.getBlogClasses(id);
	}
	@Transactional
	@Override
	public List getClassByClassNameAndUserId(String className, int id) {
		// TODO Auto-generated method stub
		return blogDao.getClassByClassNameAndUserId( className,  id);
	}
	@Transactional
	@Override
	public Blog addBlog(String titleName, String content,String className, User userValid) {
		// TODO Auto-generated method stub
		String uuid = GeneratorId.getUUID();
		Blog blog = new Blog();
		blog.setAuthor(userValid.getName());
		blog.setBlogkey(uuid);
		blog.setClassName(className);
		blog.setDate(new Date());
		blog.setName(titleName);
		blog.setUser(userValid);
		blog.setContent(content);
		blog.setCommentCount(0);
		
		BlogContent blogContent = new BlogContent();
		blogContent.setBlogkey(uuid);
		blogContent.setContent(content);
		
		blogDao.add(blog,blogContent,userValid);
		return blog;
	}
	@Transactional
	@Override
	public List<Blog> getBlogList(User userValid) {
		// TODO Auto-generated method stub
		return blogDao.getBlogList( userValid);
	}
	@Transactional
	@Override
	public List<BlogClasses> getBlogClassesList(User userValid) {
		// TODO Auto-generated method stub
		return blogDao.getBlogClassesList(userValid);
	}
	@Transactional
	@Override
	public Blog getBlogByKey(String blogKey) {
		// TODO Auto-generated method stub
		return blogDao.getBlogByKey( blogKey);
	}
	@Transactional
	@Override
	public void deleteBlog(String blogKey) {
		// TODO Auto-generated method stub
		Blog blog = null;
		BlogContent blogContent = null;
		blog = blogDao.getBlogByBlogKey(blogKey);
		blogContent = blogDao.getBlogContentByBlogKey(blogKey);
		blogDao.deleteBlog( blog,blogContent);
		
	}
	@Transactional
	@Override
	public void deleteBlogClasses(BlogClasses blogClasses,User user) {
		// TODO Auto-generated method stub
		
		blogDao.deleteBlogClass(blogClasses,user);
		blogDao.changeBlogWithClass("默认分组",blogClasses.getClassName(),user);
		
	}
	@Transactional
	@Override
	public Blog addReEditBlog(String titleName, String content, String className, String currentBlogKey,
			User userValid) {
		// TODO Auto-generated method stub
		return blogDao.addReEditBlog(titleName,content,className,currentBlogKey,userValid);
	}
	@Transactional
	@Override
	public void shareBlog(String blogkey) {
		// TODO Auto-generated method stub
		blogDao.shareBlog(blogkey);
	}
	@Transactional
	@Override
	public void cancelShareBlog(String blogkey) {
		// TODO Auto-generated method stub
		blogDao.cancelShareBlog(blogkey);
	}
	@Transactional
	@Override
	public List<Blog> getShareBlogs() {
		// TODO Auto-generated method stub
		return blogDao.getShareBlogs();
	}
	@Transactional
	@Override
	public void addBlogCommentCount(String blogkey) {
		// TODO Auto-generated method stub
		blogDao.addBlogCommentCount(blogkey);
	}
	
	
}
