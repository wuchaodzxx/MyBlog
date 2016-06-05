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
@Table(name="DB_BLOGCONTENT")
public class BlogContent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="BLOGKEY")
	private String blogkey;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="USERID")
	private String userId;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
