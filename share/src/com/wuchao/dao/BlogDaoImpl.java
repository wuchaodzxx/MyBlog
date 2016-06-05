package com.wuchao.dao;



import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.wuchao.Entity.Blog;
import com.wuchao.Entity.BlogClasses;
import com.wuchao.Entity.BlogContent;
import com.wuchao.Entity.Documents;
import com.wuchao.Entity.FileClasses;
import com.wuchao.Entity.User;


public class BlogDaoImpl implements BlogDao{
	
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
	public void addFileClasses(BlogClasses blogClasses) {
		// TODO Auto-generated method stub
		getSession().save(blogClasses);
	}

	@Override
	public List<BlogClasses> getBlogClasses(int id) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<BlogClasses> list = (List<BlogClasses>) getSession().createQuery("from BlogClasses blogClasses where blogClasses.user.id = ?").setParameter(0, id).list();
		return list;
	}

	@Override
	public List getClassByClassNameAndUserId(String className, int id) {
		// TODO Auto-generated method stub
		return (List<BlogClasses>) getSession().createQuery("from BlogClasses blogClasses where blogClasses.className = ? and blogClasses.user.id = ?").setParameter(0, className).setParameter(1, id).list();
	}

	@Override
	public void add(Blog blog, BlogContent blogContent, User userValid) {
		// TODO Auto-generated method stub
		blog.setIsShare("no");
		getSession().save(blog);
		getSession().save(blogContent);
	}

	@Override
	public List<Blog> getBlogList(User userValid) {
		// TODO Auto-generated method stub
		return (List<Blog>)getSession().createQuery("from Blog blog where  blog.user.id = ?").setParameter(0, userValid.getId()).list(); 
	}

	@Override
	public List<BlogClasses> getBlogClassesList(User userValid) {
		// TODO Auto-generated method stub
		return (List<BlogClasses>)getSession().createQuery("from BlogClasses blogClasses where  blogClasses.user.id = ?").setParameter(0, userValid.getId()).list(); 
	}

	@Override
	public Blog getBlogByKey(String blogKey) {
		// TODO Auto-generated method stub
		return (Blog) getSession().createQuery("from Blog blog where  blog.blogkey = ?").setParameter(0, blogKey).list().get(0);
	}

	@Override
	public Blog getBlogByBlogKey(String blogKey) {
		// TODO Auto-generated method stub
		return (Blog) getSession().createQuery("from Blog blog where  blog.blogkey = ?").setParameter(0, blogKey).list().get(0);
	}

	@Override
	public BlogContent getBlogContentByBlogKey(String blogKey) {
		// TODO Auto-generated method stub
		return (BlogContent) getSession().createQuery("from BlogContent blogContent where  blogContent.blogkey = ?").setParameter(0, blogKey).list().get(0);
	}

	@Override
	public void deleteBlog(Blog blog, BlogContent blogContent) {
		// TODO Auto-generated method stub
		getSession().delete(blog);
		getSession().delete(blogContent);
	}

	@Override
	public void deleteBlogClass(BlogClasses blogClasses, User user) {
		// TODO Auto-generated method stub
		blogClasses = (BlogClasses) getSession().createQuery("from BlogClasses blogClasses where  blogClasses.className = ? and blogClasses.user.name = ?").setParameter(0, blogClasses.getClassName()).setParameter(1, user.getName()).list().get(0);
		getSession().delete(blogClasses);
	}

	@Override
	public void changeBlogWithClass(String newClassName,String oldClassName, User user) {
		// TODO Auto-generated method stub
		List<Blog> list =  getSession().createQuery("from Blog blog where  blog.className = ? and blog.user.name = ?").setParameter(0, oldClassName).setParameter(1, user.getName()).list();
		for(int i=0;i<list.size();i++){
			Blog blog = list.get(i);
			blog.setClassName(newClassName);
			getSession().update(blog);
		}
	}

	@Override
	public Blog addReEditBlog(String titleName, String content, String className, String currentBlogKey,
			User userValid) {
		// TODO Auto-generated method stub
		Blog blog = (Blog) getSession().createQuery("from Blog blog where  blog.blogkey = ? ").setParameter(0, currentBlogKey).list().get(0);
		BlogContent blogContent = (BlogContent) getSession().createQuery("from BlogContent blogContent where  blogContent.blogkey = ? ").setParameter(0, currentBlogKey).list().get(0);
		blog.setName(titleName);
		blog.setDate(new Date());
		blog.setContent(content);
		blog.setClassName(className);
		
		blogContent.setContent(content);
		
		getSession().update(blog);
		getSession().update(blogContent);
		
		return blog;
	}

	@Override
	public void shareBlog(String blogkey) {
		// TODO Auto-generated method stub
		Blog blog = (Blog) getSession().createQuery("from Blog blog where blog.blogkey = ? ").setParameter(0, blogkey).list().get(0);
		blog.setIsShare("yes");
		getSession().update(blog);
	}

	@Override
	public void cancelShareBlog(String blogkey) {
		// TODO Auto-generated method stub
		Blog blog = (Blog) getSession().createQuery("from Blog blog where blog.blogkey = ? ").setParameter(0, blogkey).list().get(0);
		blog.setIsShare("no");
		getSession().update(blog);
	}

	@Override
	public List<Blog> getShareBlogs() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Blog blog where blog.isShare = ? ").setParameter(0, "yes").list();
	}

	@Override
	public void addBlogCommentCount(String blogkey) {
		// TODO Auto-generated method stub
		Blog blog = (Blog) getSession().createQuery("from Blog blog where blog.blogkey = ?").setParameter(0, blogkey).list().get(0);
		blog.setCommentCount(blog.getCommentCount()+1);
		getSession().update(blog);
	}



	

	
}
