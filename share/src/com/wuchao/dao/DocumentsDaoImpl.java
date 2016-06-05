package com.wuchao.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.wuchao.Entity.Documents;
import com.wuchao.Entity.FileClasses;
import com.wuchao.Entity.User;

public class DocumentsDaoImpl implements DocumentsDao{
	
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
	public void addDocuments(Documents documents) {
		// TODO Auto-generated method stub
		System.out.println("添加文件到数据库------用户名："+documents.getName());
		documents.setIsShare("no");
		getSession().save(documents);
	}

	@Override
	public List<Documents> findDocuments(int userId) {
		// TODO Auto-generated method stub
		List<Documents> list = (List) getSession().createQuery("from Documents documents where documents.user.id = ?").setParameter(0, userId).list();
		return list;
	}

	@Override
	public List findDocumentsByDocName(String docName) {
		// TODO Auto-generated method stub
		return   getSession().createQuery("from Documents documents where documents.name = ?").setParameter(0, docName).list();
	}

	@Override
	public void deleteDocument(Documents documents) {
		// TODO Auto-generated method stub
		System.out.println("DocumentsDaoImpl:删除文件"+documents.getName());
		//getSession().createQuery("delete from Documents documents where documents.id = ?").setParameter(0, documents.getId());
		getSession().delete(documents);
	}

	@Override
	public Documents findDocumentByDocId(String docId) {
		// TODO Auto-generated method stub
		List documents =  getSession().createQuery("from Documents documents where documents.id = ?").setParameter(0, Integer.parseInt(docId)).list();
		if(documents.size()>0){
			return (Documents) documents.get(0);
		}
		return null;
	}

	@Override
	public Documents findDocumentByDocId(int docId) {
		// TODO Auto-generated method stub
		List documents =  getSession().createQuery("from Documents documents where documents.id = ?").setParameter(0, docId).list();
		if(documents.size()>0){
			return (Documents) documents.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FileClasses> getFileClasses(int userId) {
		// TODO Auto-generated method stub
		return (List<FileClasses>) getSession().createQuery("from FileClasses fileClasses where fileClasses.user.id = ?").setParameter(0, userId).list();
	}

	@Override
	public void addFileClasses(FileClasses fileClasses) {
		// TODO Auto-generated method stub
		getSession().save(fileClasses);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getCLassByClassNameAndUserId(String className, int id) {
		// TODO Auto-generated method stub
		return (List<FileClasses>) getSession().createQuery("from FileClasses fileClasses where fileClasses.className = ? and fileClasses.user.id = ?").setParameter(0, className).setParameter(1, id).list();
	}

	@Override
	public void changeDocumentsClassName(String newClassName, String oldClassName,int userId) {
		// TODO Auto-generated method stub
		List<Documents> list = getSession().createQuery("from Documents documents where documents.className = ? and documents.user.id = ?").setParameter(0, oldClassName).setParameter(1, userId).list();
		for(int i=0;i<list.size();i++){
			list.get(i).setClassName(newClassName);
			getSession().update(list.get(i));
		}
	}

	@Override
	public void deleteClassName(String className, int userId) {
		// TODO Auto-generated method stub
		List<FileClasses> list = getSession().createQuery("from FileClasses fileClasses where fileClasses.className = ? and fileClasses.user.id = ?").setParameter(0, className).setParameter(1, userId).list();
		for(int i=0;i<list.size();i++){
			getSession().delete(list.get(i));
		}
		
	}

	@Override
	public void changeDocumentsClassName(String className, int docId) {
		// TODO Auto-generated method stub
		System.out.println("changeDocumentsClassName-更改名称："+className+"-docId:"+docId);
		Documents documents = (Documents) getSession().createQuery("from Documents documents where documents.id = ?").setParameter(0, docId).list().get(0);
		documents.setClassName(className);
		System.out.println("changeDocumentsClassName-更改后："+documents.getClassName());
		getSession().update(documents);
	}

	@Override
	public void shareDoc(int docId) {
		// TODO Auto-generated method stub
		Documents documents = (Documents) getSession().createQuery("from Documents documents where documents.id = ?").setParameter(0, docId).list().get(0);
		documents.setIsShare("yes");
		getSession().update(documents);
	}

	@Override
	public void cancelShareDoc(int docId) {
		// TODO Auto-generated method stub
		Documents documents = (Documents) getSession().createQuery("from Documents documents where documents.id = ?").setParameter(0, docId).list().get(0);
		documents.setIsShare("no");
		getSession().update(documents);
	}

	@Override
	public List<Documents> getShareDocuments() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Documents documents where documents.isShare = ?").setParameter(0, "yes").list();
	}

	@Override
	public void addDocCommentCount(String dockey) {
		// TODO Auto-generated method stub
		Documents document = (Documents) getSession().createQuery("from Documents documents where documents.dockey = ?").setParameter(0, dockey).list().get(0);
		document.setCommentCount(document.getCommentCount()+1);
		getSession().update(document);
	}



	
	
}
