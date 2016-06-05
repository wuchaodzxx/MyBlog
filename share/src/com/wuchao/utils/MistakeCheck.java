package com.wuchao.utils;

import java.util.List;

import javax.annotation.Resource;

import com.wuchao.Entity.Documents;
import com.wuchao.Entity.User;
import com.wuchao.service.DocumentsService;

import AliyunOSS.OSSService;

public class MistakeCheck {
	@Resource
	private OSSService ossService;
	@Resource
	private DocumentsService documentsService;
	
	//检查用户数据库的文件和OSS的是否一致
	public void checkOssAndSqlMatch(User user){
		List<Documents> documentsList = documentsService.findDocuments(user.getId());
		for(int i=0;i<=documentsList.size();i++){
			if(!ossService.isExsist(user.getId()+"/"+documentsList.get(i).getName())){
				documentsService.deleteDocument(documentsList.get(i));
				System.out.println("MistakeCheck.checkOssAndSqlMatch:"+documentsList.get(i).getName());
			}
		}
	}
}
