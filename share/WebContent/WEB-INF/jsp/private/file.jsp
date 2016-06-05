<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="<%=request.getContextPath()%>/js/jquery-1.12.3.min.js"></script>
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
		xhr.open("POST", "/share/private/upload.do?className="
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
<script>
	function addClass(className) {
		var xhr = new XMLHttpRequest();
		xhr.addEventListener("load", uploadComplete, false);
		xhr.addEventListener("error", uploadFailed, false);
		xhr.open("GET", "/share/private/addClass.do?className="
				+ encodeURIComponent(className));//修改成自己的接口
		xhr.send(null);
	}
	function addClassSeletct() {
		var className = prompt("分组名称", "请输入新的分组名称")
		if(className!=null){
			addClass(className);
		}
		

	}

	//删除空白td标签
	function deleteTd() {
		var tdList = document.getElementsByTagName("td");
		for (var i = 0; i < tdList.length; i++) {
			if (tdList[i].innerText == "" || tdList[i].innerText == "undefined") {
				tdList[i].parentNode.removeChild(tdList[i]);

			}

		}
	}
	function selectList(listName,id) {
		
		var fileTableList = document.getElementsByName("fileTable");
		//var fileList = document.getElementsByName("listName")[0];
		for (var i = 0; i < fileTableList.length; i++) {
			if(fileTableList.item(i).id==listName){
				fileTableList.item(i).style.display = "";
			}else{
				fileTableList.item(i).style.display = "none";
			}
			
		}
		var fileLeftList = document.getElementById("classListLeft").getElementsByTagName("a");
		for (var i = 0; i < fileLeftList.length; i++) {
			
			if(fileLeftList.item(i).id=="selectedLeft"){
				fileLeftList.item(i).id = fileLeftList.item(i).name;
				
			}else{
				
			}
			
		}
		document.getElementById(id).id="selectedLeft";
		deleteTd();
		
	}
	function deleteClass(className){
		if(className=="默认分组"){
			alert("默认分组无法删除！");
		}else{
			var r=confirm("确认要删除分组："+"'"+className+"'"+"?");
			if(r){
				var xhr = new XMLHttpRequest();
				xhr.addEventListener("load", uploadComplete, false);
				xhr.addEventListener("error", uploadFailed, false);
				xhr.open("GET", "/share/private/deleteClass.do?className="
						+ encodeURIComponent(className));//修改成自己的接口
				xhr.send(null);
			}
		}
		
		
	}
	function changeClassName(docId){
		var id = "fileClassesForChange"+docId;
		var className = document.getElementById(id).value;
		
		var xhr = new XMLHttpRequest();
		xhr.addEventListener("load", uploadComplete, false);
		xhr.addEventListener("error", uploadFailed, false);
		xhr.open("GET", "/share/private/changeDocClass.do?className="
				+ encodeURIComponent(className)+"&"+"docId="+encodeURIComponent(docId));//修改成自己的接口
		xhr.send(null);
		
	}
	
	function shareDoc(docId){
		var xhr = new XMLHttpRequest();
		xhr.addEventListener("load", shareComplete, false);
		xhr.addEventListener("error", uploadFailed, false);
		xhr.open("GET", "/share/private/shareDoc.do?docId="+encodeURIComponent(docId));//修改成自己的接口
		xhr.send(null);
		
	}
	function shareComplete(){
		alert("分享成功！");
		location.reload();
	}
	function cancelShareDoc(docId){
		var xhr = new XMLHttpRequest();
		xhr.addEventListener("load", cancelShareComplete, false);
		xhr.addEventListener("error", uploadFailed, false);
		xhr.open("GET", "/share/private/cancelShareDoc.do?docId="+encodeURIComponent(docId));//修改成自己的接口
		xhr.send(null);
	}
	function cancelShareComplete(){
		alert("取消分享成功！");
		location.reload();
	}
</script>
<script type="text/javascript">

</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>//css//style.css" type="text/css" />
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>//css//file.css" type="text/css" />


</head>
<body>
	<div class="header" id="header">
		
		<ul>
			<li><a href="/share/private//main.do">主页</a></li>
			<li class="selected" ><a href="/share/private//file.do" >文件</a></li>
			<li><a href="/share/private//blog.do" >博文</a></li>
			<li><a href="/share/public//share.do" >分享</a></li>
			<li><a href="/share/private//logout.do">注销</a></li>
		</ul>
		<div class="showUserName">hello,${userValid.nickName}</div>
	</div>
	<br/>
	<!-- 左侧列表 -->
	<div class="left">
		<div class="classList" id="classListLeft">
			<img src='<%=request.getContextPath()%>//images//tag12.jpg'/><a  href="#" onclick="selectList(this.name,this.id)" name="mainTable" id="selectedLeft" >全部</a> <br />
			<c:forEach var="fileClasses" items="${fileClassesList}"
				varStatus="status">
				<img src='<%=request.getContextPath()%>//images//tag10.jpg'/>
				<a href="#" onclick="selectList(this.name,this.id)"  id="${fileClasses.className}LeftList"
					name="${fileClasses.className}Table" >${fileClasses.className} </a>
				<img alt="" id="${fileClasses.className}"
							height="10" width="10"
							onclick="deleteClass(this.id)"
							src="<%=request.getContextPath()%>//images//delete.png" />
				<br/>
			</c:forEach>
		</div>
	</div>

	<div class="main">
		<div class="fileUpLoad">
			<form method="post" enctype="multipart/form-data"
				style="height: 35px;">
				<div class="fileInput">
					<!-- 传输文件分类 -->
					<input id="className" type="text" name="className" value="默认"
						style="display: none" />
					<!-- 下面为文件上传 -->
					<input id="file" type="file" name="file" class="file"
						onchange="fileSelected()" />
				</div>
				<!-- <div id="fileSize" class="fileSize"></div>
				<div id="fileType" class="fileType"></div> -->

				<div class="row">
					<input type="button" onclick="uploadFile()" value="Upload" />
					<!-- <input id="fileClass" type="text" style="display: none" name="className" /> -->
					<!-- 下面为选择分类 -->
					<select class="fileClasses" id="fileClasses"
						onblur="selectChange()">
						<c:forEach var="fileClasses" items="${fileClassesList}"
							varStatus="status">
							<option>${fileClasses.className}</option>
						</c:forEach>
					</select>
					<!-- 下面为添加分类 -->
					<a onclick="addClassSeletct()" class="addClass" href="#">添加分类</a>

				</div>

				<div id="progressNumber"></div>
				

			</form>

		</div>

		<br /> <br /> <br /><br />
		剩余空间：${userValid.leftStorageSpaceSize}
		<div class="fileList">

			<table id="mainTable" name="fileTable">
				<tr class="tableTile">
					<td>序数</td>
					<td>文件名</td>
					<td>文件大小</td>
					<td>上传日期</td>
					<td>上传用户</td>
					<td>类别</td>
					<td>操作</td>
				</tr>
				<c:forEach var="doc" items="${documentsList}" varStatus="status">
					<tr id="main${status.index + 1}" name="${status.index + 1}">
						<td>${status.index + 1}<td />

						<td class="td_fileName" title="${doc.name}"><img alt=""
							height="20" width="20"
							src="<%=request.getContextPath()%>//images//${doc.icon}" /> <a
							name="docNameLink" href="${doc.url}">${doc.name}</a>
						
						<td />

						<td>${doc.size}
						
						<td />

						<td name="docDate">${doc.date}
						
						<td />

						<td>${doc.author}
						
						<td />

						<td>${doc.className}
						
						<td />

						<td>
							<a href="/share/private/deleteDoc.do?docId=${doc.id}">删除</a>
							
							<select  id="fileClassesForChange${doc.id}" 
								onblur="selectChange()" >
								<c:forEach var="fileClasses" items="${fileClassesList}"
									varStatus="status">
									<option>${fileClasses.className}</option>
								</c:forEach>
							</select>
							<a id="${doc.id}" href="#" onclick="changeClassName(this.id)">选择分类</a>
							<c:choose>
										<c:when test="${doc.isShare=='yes'}">
											<a id="${doc.id}" href="#" onclick="cancelShareDoc(this.id)">取消分享</a>
										</c:when>
										<c:when test="${doc.isShare=='no'}">
											<a id="${doc.id}" href="#" onclick="shareDoc(this.id)">分享</a>
										</c:when>
							</c:choose>
						</td>
					
					<tr/>
				</c:forEach>
			</table>
			<!-- 下面根据类别生成对应表格 --> 
			<c:forEach var="fileClasses" items="${fileClassesList}">
				
				<table id="${fileClasses.className}Table" name="fileTable" style="display: none">
					<tr class="tableTile" >
						<td>文件名</td>
						<td>文件大小</td>
						<td>上传日期</td>
						<td>上传用户</td>
						<td>类别</td>
						<td>操作</td>
					</tr>
					
					<c:forEach var="doc" items="${documentsList}" varStatus="status">
						
						<c:if test="${doc.className==fileClasses.className}">
							<tr>
								<td class="td_fileName" title="${doc.name}"><img alt=""
													height="20" width="20"
													src="<%=request.getContextPath()%>//images//${doc.icon}" /><a
													name="docNameLink" href="${doc.url}">${doc.name}</a>
								<td />

								<td>${doc.size}<td/>

								<td name="docDate">${doc.date}<td/>

								<td>${doc.author}<td/>

								<td>${doc.className}<td/>
								<td>
									<a href="/share/private/deleteDoc.do?docId=${doc.id}">删除</a>
									
									<select  id="fileClassesForChange${doc.id}" 
											onblur="selectChange()" >
										<c:forEach var="fileClassesOption" items="${fileClassesList}"
											varStatus="status">
											<option>${fileClassesOption.className}</option>
										</c:forEach>
									</select>
									<a id="${doc.id}" href="#" onclick="changeClassName(this.id)">选择分类</a>
									<c:choose>
										<c:when test="${doc.isShare=='yes'}">
											<a id="${doc.id}" href="#" onclick="cancelShareDoc(this.id)">取消分享</a>
										</c:when>
										<c:when test="${doc.isShare=='no'}">
											<a id="${doc.id}" href="#" onclick="shareDoc(this.id)">分享</a>
										</c:when>
									</c:choose>
									
									
								</td>
							</tr>
						</c:if>

					</c:forEach>
				</table>
			</c:forEach>
			
		</div>
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

<script>
	//使用js代码解决表格内容过长的问题
	(function() {
		var docNameLinkList = document.getElementsByName("docNameLink");
		var i;
		for (i = 0; i < docNameLinkList.length; i++) {
			if (docNameLinkList[i].innerText.length > 20) {
				docNameLinkList[i].innerText = docNameLinkList[i].innerText
						.substring(0, 19)
						+ "...";
			}
		}

		var docDateList = document.getElementsByName("docDate");
		var j;
		for (j = 0; j < docDateList.length; j++) {

			docDateList[j].innerText = docDateList[j].innerText
					.substring(0, 10);
		}

	})();
	//删除空白<td>
	deleteTd();
</script>
<script type="text/javascript">
	function contanctMe(){
		alert("邮箱：wuchaodzxx@126.com\nQQ:1061616449");
	}
	$(document).ready(
		function(){
			alert("ready");
		}	
	);
</script>
</body>
</html>