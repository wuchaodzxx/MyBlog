package com.wuchao.utils;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.wuchao.Entity.Documents;
import com.wuchao.Entity.FileClasses;
import com.wuchao.Entity.User;
import com.wuchao.service.DocumentsService;
import com.wuchao.service.FileUpLoadService;

public class Utils2Files {
	static Map<String,String> mapIco = new HashMap();
	static Map<String,String> mapType = new HashMap();
	static{
		
		mapIco.put("html", "html.ico");
		mapIco.put("xlsx", "excel.ico");
		mapIco.put("xlsm", "excel.ico");
		mapIco.put("xlsm", "excel.ico");
		mapIco.put("xltx", "excel.ico");
		mapIco.put("xltm", "excel.ico");
		mapIco.put("xlam", "excel.ico");
		mapIco.put("xlsb", "excel.ico");
		mapIco.put("doc", "word.ico");
		mapIco.put("docx", "word.ico");
		mapIco.put("dot ", "word.ico");
		mapIco.put("dotx ", "word.ico");
		mapIco.put("dotx ", "word.ico");
		mapIco.put("docm ", "word.ico");
		mapIco.put("dotm ", "word.ico");
		mapIco.put("xml ", "xml.ico");
		mapIco.put("exe", "exe.ico");
		mapIco.put("file", "file.ico");
		mapIco.put("BMP", "image.ico");
		mapIco.put("bmp", "image.ico");
		mapIco.put("PCX", "image.ico");
		mapIco.put("pcx", "image.ico");
		mapIco.put("TIFF", "image.ico");
		mapIco.put("tiff", "image.ico");
		mapIco.put("GIF", "image.ico");
		mapIco.put("gif", "image.ico");
		mapIco.put("JPEG", "image.ico");
		mapIco.put("jpeg", "image.ico");
		mapIco.put("jpg", "image.ico");
		mapIco.put("PNG", "image.ico");
		mapIco.put("png", "image.ico");
		mapIco.put("pdf", "pdf.ico");
		mapIco.put("ppt", "ppt.ico");
		mapIco.put("pptx", "ppt.ico");
		mapIco.put("pps", "ppt.ico");
		mapIco.put("rar", "rar.ico");
		mapIco.put("tar", "rar.ico");
		mapIco.put("jar", "rar.ico");
		mapIco.put("zip", "zip.ico");
		mapIco.put("zip", "zip.ico");
		mapIco.put("zip", "zip.ico");
		mapIco.put("7-zip", "zip.ico");
		mapIco.put("txt", "txt.ico");
		mapIco.put("txt", "txt.ico");
		mapIco.put("avi", "video.ico");
		mapIco.put("mpg", "video.ico");
		mapIco.put("mpeg", "video.ico");
		mapIco.put("rm", "video.ico");
		mapIco.put("rmvb", "video.ico");
		mapIco.put("dat", "video.ico");
		mapIco.put("wmv", "video.ico");
		mapIco.put("mov", "video.ico");
		mapIco.put("ram", "video.ico");
		mapIco.put("mkv", "video.ico");
		mapIco.put("mp4", "video.ico");
		mapIco.put("3gp", "video.ico");
		mapIco.put("mpv2", "video.ico");
		mapIco.put("avi", "video.ico");
		mapIco.put("avi", "video.ico");
		mapIco.put("avi", "video.ico");
		mapIco.put("avi", "video.ico");
		mapIco.put("avi", "video.ico");
		mapIco.put("avi", "video.ico");
		mapIco.put("wav", "mp3.ico");
		mapIco.put("mp3", "mp3.ico");
		mapIco.put("rm", "mp3.ico");
		mapIco.put("rm", "mp3.ico");
		mapIco.put("ape", "mp3.ico");
		mapIco.put("flac", "mp3.ico");
		mapIco.put("acc", "mp3.ico");
		
		
		
		mapType.put("html", "html");
		mapType.put("xlsx", "excel");
		mapType.put("xlsm", "excel");
		mapType.put("xlsm", "excel");
		mapType.put("xltx", "excel");
		mapType.put("xltm", "excel");
		mapType.put("xlam", "excel");
		mapType.put("xlsb", "excel");
		mapType.put("doc", "word");
		mapType.put("docx", "word");
		mapType.put("dot ", "word");
		mapType.put("dotx ", "word");
		mapType.put("dotx ", "word");
		mapType.put("docm ", "word");
		mapType.put("dotm ", "word");
		mapType.put("xml ", "xml");
		mapType.put("js ", "js");
		mapType.put("css ", "css");
		mapType.put("exe", "exe");
		mapType.put("file", "file");
		mapType.put("BMP", "image");
		mapType.put("bmp", "image");
		mapType.put("PCX", "image");
		mapType.put("pcx", "image");
		mapType.put("TIFF", "image");
		mapType.put("tiff", "image");
		mapType.put("GIF", "image");
		mapType.put("gif", "image");
		mapType.put("JPEG", "image");
		mapType.put("jpeg", "image");
		mapType.put("jpg", "image");
		mapType.put("PNG", "image");
		mapType.put("png", "image");
		mapType.put("pdf", "pdf");
		mapType.put("ppt", "ppt");
		mapType.put("pptx", "ppt");
		mapType.put("pps", "ppt");
		mapType.put("rar", "rar");
		mapType.put("tar", "rar");
		mapType.put("jar", "rar");
		mapType.put("zip", "zip");
		mapType.put("zip", "zip");
		mapType.put("zip", "zip");
		mapType.put("7-zip", "zip");
		mapType.put("txt", "txt");
		mapType.put("txt", "txt");
		mapType.put("avi", "video");
		mapType.put("mpg", "video");
		mapType.put("mpeg", "video");
		mapType.put("rm", "video");
		mapType.put("rmvb", "video");
		mapType.put("dat", "video");
		mapType.put("wmv", "video");
		mapType.put("mov", "video");
		mapType.put("ram", "video");
		mapType.put("mkv", "video");
		mapType.put("mp4", "video");
		mapType.put("3gp", "video");
		mapType.put("mpv2", "video");
		mapType.put("avi", "video");
		mapType.put("avi", "video");
		mapType.put("avi", "video");
		mapType.put("avi", "video");
		mapType.put("avi", "video");
		mapType.put("avi", "video");
		mapType.put("wav", "audio");
		mapType.put("mp3", "audio");
		mapType.put("rm", "audio");
		mapType.put("rm", "audio");
		mapType.put("ape", "audio");
		mapType.put("flac", "audio");
		mapType.put("acc", "audio");
		
		
	}
	public String reNameDoc(String docName,DocumentsService documentsService, int userId){
		//List<Documents> documents = documentsService.findDocuments(userId);
		
		int length = docName.length();
		if(length>150){
			docName=docName.substring(length-150-1, length-1);
			System.out.println("w文件名过长，执行截断。");
		}
		List<Documents> documents = documentsService.findDocumentsByDocName(docName);
		
		if(documents.size()>0){
			
			System.out.println("Utils2Files.reNameDoc:发现重名！");
			String names[] = docName.split("\\.");
			String name="";
			for(int j =0;j<names.length;j++){
				if(j==names.length-1){
					name=name+"("+GeneratorId.getShortUUID()+")."+names[j];
				}else{
					if(name==""){
						name=names[j];
					}else{
						name=name+"."+names[j];
					}
					
				}
			}
			return name;
		}
		return docName;
	}

	public static String setFileIcon(String contentType) {
		// TODO Auto-generated method stub

		String iconType=null;
		System.out.println("ico_orignal:"+contentType);
		try{
			String[] str = contentType.split("\\.");
			int a = str.length;
			iconType = str[a-1];
			System.out.println("iconType:"+iconType);
			
		}catch (Exception e){
			System.out.println("setFileIcon:error");
			System.out.println(e.getMessage());
			return "file.ico";
		}
		String ico = mapIco.get(iconType);
		if(ico==null){
			return mapIco.get("file");
		}else{
			System.out.println("ico:"+ico);
			return ico;
		}
		
	}
	
	public static String setFileType(String contentType) {
		// TODO Auto-generated method stub

		String iconType=null;
		System.out.println("ico_orignal:"+contentType);
		try{
			String[] str = contentType.split("\\.");
			int a = str.length;
			iconType = str[a-1];
			System.out.println("iconType:"+iconType);
			
		}catch (Exception e){
			System.out.println("setFileIcon:error");
			System.out.println(e.getMessage());
			return "file.ico";
		}
		String type = mapType.get(iconType);
		if(type==null){
			return mapIco.get("file");
		}else{
			
			return type;
		}
		
	}
	
	//size单位为bit，要转换为合适的单位
	public static String getSizeFormat(long size) {
		// TODO Auto-generated method stub
		DecimalFormat df = new DecimalFormat("#.##");  
         //System.out.println(df.format(f));  
		double size_o = size;
		String tag="";
		double size_f=0;
		if(size<1048576){
			tag="K";
			size_f=size_o/1024;
		}
		if(size>=1048576&&size_o<1073741824){
			tag="M";
			size_f=size_o/(1024*1024);
		}
		if(size>=1073741824){
			tag="G";
			size_f=size_o/(1024*1024*1024);
		}
		return df.format(size_f)+tag;
	}
	public static Map<String, Documents> getMapWithDocumentsList(List<Documents> documentsList,List<FileClasses> fileClassesList){
		Map<String,Documents> docWithClasses = new HashMap<String,Documents>();
		for(int i=0;i<fileClassesList.size();i++){
			for(int j=0;j<documentsList.size();j++){
				if(fileClassesList.get(i).getClassName()==documentsList.get(j).getClassName()){
					docWithClasses.put(fileClassesList.get(i).getClassName(), documentsList.get(j));
				}
			}
		}
		return docWithClasses;
	}
	public static boolean typeMatch(String contentType,String matchedType){
		if(matchedType=="image"){
			String str = contentType.split("/")[0];
			System.out.println("typeMatch:"+str);
			return str.equals(matchedType);
		}
		
		return false;
	}
}
//2380696
//2325k