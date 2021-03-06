package com.wuchao.Entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DB_COMMENTOFBLOG")
public class CommentOfBlog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="BLOGKEY")
	private String blogkey;
	
	@Column(name="USERNAME")
	private String userName;
	
	@Column(name="USERID")
	private int userId;


	@Column(name="DATE")
	private Date date;
	
	@Column(name="COMMENTTEXT")
	private String commentText;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBlogkey() {
		return blogkey;
	}

	public void setBlogkey(String blogkey) {
		this.blogkey = blogkey;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	
}
