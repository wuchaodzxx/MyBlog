package com.wuchao.Entity;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Range;
import com.wuchao.annoation.Email;
import com.wuchao.annoation.Name;

@Entity
@Table(name="DB_USER")
public class User {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id=0;
	
	@OneToMany(mappedBy="user")//Many一方必须含有user属性，该句表示由One一方指向many一方
	private Set<Documents> docments = new HashSet<Documents>();
	
	@OneToMany(mappedBy="user")//Many一方必须含有user属性，该句表示由One一方指向many一方
	private Set<Attachment> Aattachment = new HashSet<Attachment>();
	
	@OneToMany(mappedBy="user")//Many一方必须含有user属性，该句表示由One一方指向many一方
	private Set<Blog> blog = new HashSet<Blog>();
	

	@OneToMany(mappedBy="user")
	private List<FileClasses> fileClasses = new ArrayList<FileClasses>();
	
	@OneToMany(mappedBy="user")
	private List<BlogClasses> blogClasses = new ArrayList<BlogClasses>();
	
	

	@Column(name="NAME")
	@Name(maxL=20,minL=5,message = "名字长度必须在5-20")
	private String name;
	
	@Column(name="AGE")
	@Range(min=1,message="年龄为大于0的数字")
	private int age;
	
	@Column(name="PASSWORD")
	@Name(maxL=20,minL=5,message = "密码长度必须在5-20")
	private String password;
	
	@Column(name="PASSWORD_MD5")
	private String password_md5;
	
	public String getPassword_md5() {
		return password_md5;
	}
	public void setPassword_md5(String password_md5) {
		this.password_md5 = password_md5;
	}
	@Column(name="NICKNAME")
	@Name(maxL=20,minL=2,message = "昵称长度必须在5-20")
	private String nickName;
	
	@Column(name="EMAIL")
	@Email(required="false",message="邮箱格式有误")
	private String email;
	
	@Column(name="STORAGESPACE")
	private long storageSpace=0;
	
	@Column(name="LEFTSTORAGESPACE")
	private long leftStorageSpace;
	
	@Column(name="LEFTSTORAGESPACESIZE")
	private String leftStorageSpaceSize;//数值等同于leftStorageSpace
	

	public String getLeftStorageSpaceSize() {
		return leftStorageSpaceSize;
	}
	public void setLeftStorageSpaceSize(String leftStorageSpaceSize) {
		this.leftStorageSpaceSize = leftStorageSpaceSize;
	}
	public long getLeftStorageSpace() {
		return leftStorageSpace;
	}
	public void setLeftStorageSpace(long leftStorageSpace) {
		this.leftStorageSpace = leftStorageSpace;
	}
	public long getStorageSpace() {
		return storageSpace;
	}
	public void setStorageSpace(long storageSpace) {
		this.storageSpace = storageSpace;
	}
	public List<BlogClasses> getBlogClasses() {
		return blogClasses;
	}
	public void setBlogClasses(List<BlogClasses> blogClasses) {
		this.blogClasses = blogClasses;
	}
	public List<FileClasses> getFileClasses() {
		return fileClasses;
	}
	public void setFileClasses(List<FileClasses> fileClasses) {
		this.fileClasses = fileClasses;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Documents> getDocments() {
		return docments;
	}

	public void setDocments(Set<Documents> docments) {
		this.docments = docments;
	}

	public Set<Attachment> getAattachment() {
		return Aattachment;
	}
	public void setAattachment(Set<Attachment> aattachment) {
		Aattachment = aattachment;
	}
	
	public Set<Blog> getBlog() {
		return blog;
	}
	public void setBlog(Set<Blog> blog) {
		this.blog = blog;
	}
	
}
