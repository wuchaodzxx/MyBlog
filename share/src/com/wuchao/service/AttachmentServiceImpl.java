package com.wuchao.service;

import org.springframework.transaction.annotation.Transactional;
import com.wuchao.Entity.Attachment;
import com.wuchao.dao.AttachmentDao;




public class AttachmentServiceImpl implements AttachmentService {
	private AttachmentDao attachmentDao;

	public AttachmentDao getAttachmentDao() {
		return attachmentDao;
	}
	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}
	

	@Transactional
	@Override
	public void addAttachment(Attachment attachment) {
		// TODO Auto-generated method stub
		attachmentDao.addAttachment(attachment);
	}





}
