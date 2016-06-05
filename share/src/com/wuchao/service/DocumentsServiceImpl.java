package com.wuchao.service;


import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import com.wuchao.Entity.Documents;
import com.wuchao.Entity.FileClasses;
import com.wuchao.Entity.User;
import com.wuchao.dao.DocumentsDao;



public class DocumentsServiceImpl implements DocumentsService {
	private DocumentsDao documentsDao;
	
	public DocumentsDao getDocumentsDao() {
		return documentsDao;
	}

	public void setDocumentsDao(DocumentsDao documentsDao) {
		this.documentsDao = documentsDao;
	}

	@Transactional
	@Override
	public void addDocuments(Documents documents) {
		// TODO Auto-generated method stub
		documentsDao.addDocuments(documents);
	}
	@Transactional
	@Override
	public List<Documents> findDocuments(int userId) {
		// TODO Auto-generated method stub
		return documentsDao.findDocuments(userId);
	}
	@Transactional
	@Override
	public List<Documents> findDocumentsByDocName(String docName) {
		// TODO Auto-generated method stub
		return documentsDao.findDocumentsByDocName(docName);
	}
	@Transactional
	@Override
	public void deleteDocument(Documents documents) {
		// TODO Auto-generated method stub
		documentsDao.deleteDocument(documents);
	}
	@Transactional
	@Override
	public Documents findDocumentByDocId(String docId) {
		// TODO Auto-generated method stub
		return documentsDao.findDocumentByDocId(docId);
	}
	@Transactional
	@Override
	public Documents findDocumentByDocId(int docId) {
		// TODO Auto-generated method stub
		return documentsDao.findDocumentByDocId(docId);
	}
	@Transactional
	@Override
	public List<FileClasses> getFileClasses(int userId) {
		// TODO Auto-generated method stub
		return documentsDao.getFileClasses(userId);
	}
	@Transactional
	@Override
	public void addFileClasses(FileClasses fileClasses) {
		// TODO Auto-generated method stub
		documentsDao.addFileClasses(fileClasses);
	}
	@Transactional
	@Override
	public List getCLassByClassNameAndUserId(String className, int id) {
		// TODO Auto-generated method stub
		return documentsDao.getCLassByClassNameAndUserId(className,id);
	}
	@Transactional
	@Override
	public void changeDocumentsClassName(String newClassName, String oldClassName,int userId) {
		// TODO Auto-generated method stub
		documentsDao.changeDocumentsClassName(newClassName,oldClassName,userId);
	}
	@Transactional
	@Override
	public void deleteClassName(String className, int userId) {
		// TODO Auto-generated method stub
		
		if(!(className.equals("默认分组"))){
			documentsDao.deleteClassName( className,  userId);
		}
		
	}
	@Transactional
	@Override
	public void changeDocumentsClassName(String className, int docId) {
		// TODO Auto-generated method stub
		documentsDao.changeDocumentsClassName(className,docId);
	}
	@Transactional
	@Override
	public void shareDoc(int docId) {
		// TODO Auto-generated method stub
		documentsDao.shareDoc(docId);
	}
	@Transactional
	@Override
	public void cancelShareDoc(int docId) {
		// TODO Auto-generated method stub
		documentsDao.cancelShareDoc(docId);
	}
	@Transactional
	@Override
	public List<Documents> getShareDocuments() {
		// TODO Auto-generated method stub
		return documentsDao.getShareDocuments();
	}
	@Transactional
	@Override
	public void addDocCommentCount(String dockey) {
		// TODO Auto-generated method stub
		documentsDao.addDocCommentCount(dockey);
	}
	
}
