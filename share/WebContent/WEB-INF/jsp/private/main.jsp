<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script type="text/javascript">
	function fileSelected() {
		var file = document.getElementById('file').files[0];
		if (file) {
			var fileSize = 0;
			if (file.size > 1024 * 1024)
				fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100)
						.toString()
						+ 'MB';
			else
				fileSize = (Math.round(file.size * 100 / 1024) / 100)
						.toString()
						+ 'KB';
			document.getElementById('fileSize').innerHTML = '文件大小: ' + fileSize;
			document.getElementById('fileType').innerHTML = '文件类型: '
					+ file.type;
		}
	}
	function uploadFile() {
		var className = document.getElementById("fileClasses").value;
		var fd = new FormData();
		fd.append("file", document.getElementById('file').files[0]);
		var xhr = new XMLHttpRequest();
		xhr.upload.addEventListener("progress", uploadProgress, false);
		xhr.addEventListener("load", fileUploadComplete, false);
		xhr.addEventListener("error", uploadFailed, false);
		xhr.addEventListener("abort", uploadCanceled, false);
		xhr.open("POST", "/01project/private/upload.do?className="
				+ encodeURIComponent(className));//修改成自己的接口
		xhr.send(fd);
	}
	function uploadProgress(evt) {
		if (evt.lengthComputable) {
			var percentComplete = Math.round(evt.loaded * 100 / evt.total-1);
			document.getElementById('progressNumber').innerHTML = percentComplete
					.toString()
					+ '%...';
		} else {
			document.getElementById('progressNumber').innerHTML = 'unable to compute';
		}
	}
	function fileUploadComplete(evt) {
		/* 服务器端返回响应时候触发event事件*/
		document.getElementById('progressNumber').innerHTML = "上传成功！"
		location.reload();

	}
	function uploadComplete(evt) {
		/* 服务器端返回响应时候触发event事件*/
		location.reload();

	}
	function uploadFailed(evt) {
		alert("There was an error attempting to upload the file.");
	}
	function uploadCanceled(evt) {
		alert("The upload has been canceled by the user or the browser dropped the connection.");
	}
</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>//css//style.css" type="text/css" />
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>//css//main.css" type="text/css" />
<script src="<%=request.getContextPath()%>//js//jquery-2.2.2.js"></script>
</head>
<body>
	<div class="header" id="header">

		<ul>
			<li class="selected" ><a href="/share/private//main.do">主页</a></li>
			<li><a href="/share/private//file.do" >文件</a></li>
			<li><a href="/share/private//blog.do" >博文</a></li>
			<li><a href="/share/public//share.do" >分享</a></li>
			<li><a href="/share/private//logout.do">注销</a></li>
		</ul>
		<div class="showUserName">hello,${userValid.nickName}</div>
	</div>
	<br/>
	<!-- 左侧列表 -->
	<div class="left">
		
	</div>

	<div class="main">
		网站建立于2016年3月21日，发布于2016年4月14日。该网站长期发布在阿里云ECS服务器，数据库驻在ECS中，文件存储在OSS。<br/>
		网站目前为止主要包含三个模块。<br/>
		第一：文件上传服务。单个文件大小最好小于1G（目前单个用户的存储空间仅为1G）。<br/>
		第二：文章记录服务。前端采用可视化富文本编辑器CKeditor，编辑功能更加多样化，更可以在文中动态插入图片。目前还不能在文章中插入其他附件。<br/>
		第三：文件及文章分享服务，通过分享使别人看到你所分析的内容并可添加评论，目前不支持回复评论。
		用户密码采用32位的md5加密算法，保证信息安全。<br/>
		测试过程中发现少数浏览器不兼容而产生大部分功能无法使用。建议使用基于webkit内核的浏览器。<br/>
		测试结果如下：<br/>
		兼容的浏览器：Firefox火狐浏览器（45.0.1），chrome谷歌浏览器（49.0.2623.110m），IE(11)，百度浏览器（7.6.100.2089），360急速浏览器（8.5.0.136 急速模式，IE11模式）<br/>
		不兼容浏览器：360急速浏览器（8.5.0.136 兼容模式）<br/>
		<a href="#" onclick="contanctMe()">点击这里联系我</a>
	</div>

	<div class="foot">
		<ul>
					<li><a href="#">主页</a></li>
					<li><a href="#">关于</a></li>
					<li><a href="#">博客</a></li>
					<li><a href="#">吴超</a></li>
					<li><a href="#" onclick="contanctMe()">联系我</a></li>
		</ul>
				<p>&#169; Copyright &#169; 2016. Company name all rights reserved</p>
	</div>


</body>
<script type="text/javascript">
	function contanctMe(){
		alert("邮箱：wuchaodzxx@126.com\nQQ:1061616449");
	}
	</script>
</html>