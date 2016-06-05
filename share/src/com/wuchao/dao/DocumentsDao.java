package com.wuchao.dao;

import java.util.List;

import com.wuchao.Entity.Documents;
import com.wuchao.Entity.FileClasses;
import com.wuchao.Entity.User;
public interface DocumentsDao {
	public void addDocuments(Documents documents);
	public List<Documents> findDocuments(int userId);
	public List<Documents> findDocumentsByDocName(String docName);
	public void deleteDocument(Documents documents);
	public Documents findDocumentByDocId(String docId);
	public Documents findDocumentByDocId(int docId);
	public List<FileClasses> getFileClasses(int userId);
	public void addFileClasses(FileClasses fileClasses);
	public List getCLassByClassNameAndUserId(String className, int id);
	public void changeDocumentsClassName(String newClassName, String oldClassName,int userId);
	public void deleteClassName(String className, int userId);
	public void changeDocumentsClassName(String className, int docId);
	public void shareDoc(int docId);
	public void cancelShareDoc(int docId);
	public List<Documents> getShareDocuments();
	public void addDocCommentCount(String dockey);
}
