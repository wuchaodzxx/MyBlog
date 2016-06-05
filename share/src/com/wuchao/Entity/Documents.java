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
@Table(name="DB_DOCUMENTS")
public class Documents {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="DOCKEY")
	private String dockey;
	
	@Column(name="URL")
	private String url;//文章在oss的下载地址
	
	public String getDockey() {
		return dockey;
	}
	public void setDockey(String dockey) {
		this.dockey = dockey;
	}
	@Column(name="DATE")
	private Date date;
	
	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="AUTHOR")
	private String author;
	
	@Column(name="MD5")
	private String md5;
	
	@Column(name="ICON")
	private String icon;
	
	@Column(name="SIZE")
	private String size;
	
	@Column(name="ORIGNALSIZE")
	private long orignalSize=0;
	
	@Column(name="ISSHARE")
	private String isShare;
	
	@Column(name="COMMENTCOUNT")
	private int commentCount=0;

	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public String getIsShare() {
		return isShare;
	}
	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}
	public long getOrignalSize() {
		return orignalSize;
	}
	public void setOrignalSize(long orignalSize) {
		this.orignalSize = orignalSize;
	}
	@Column(name="CLASSNAME")
	private String className;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@ManyToOne
	@JoinColumn(name="USER_Id")
	private User user;
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
