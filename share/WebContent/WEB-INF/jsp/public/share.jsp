<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script type="text/javascript">
	function shareFileListOnclick() {
		var elementFileList = document.getElementById("fileList");
		var elementBlog = document.getElementById("blogShow");
		var elementBlogList = document.getElementById("blogList");
		var elementCommentAeraDoc = document.getElementById("commentAeraOfDoc");
		var elementCommentAeraBlog = document.getElementById("commentAeraOfBlog");
		

		elementFileList.style.display = "";
		elementBlog.style.display = "none";
		elementBlogList.style.display = "none";
		elementCommentAeraDoc.style.display = "none";
		elementCommentAeraBlog.style.display = "none";

	}
	function shareBlogListOnclick() {
		var elementFileList = document.getElementById("fileList");
		var elementBlog = document.getElementById("blogShow");
		var elementBlogList = document.getElementById("blogList");
		var elementCommentAeraDoc = document.getElementById("commentAeraOfDoc");
		var elementCommentAeraBlog = document.getElementById("commentAeraOfBlog");

		elementFileList.style.display = "none";
		elementBlog.style.display = "none";
		elementBlogList.style.display = "";
		elementCommentAeraDoc.style.display = "none";
		elementCommentAeraBlog.style.display = "none";

	}

	function getBlogClick(blogKey) {

		//不同的浏览器获取对象xmlhttprequest 对象方法不一样
		if (window.ActiveXObject) {

			xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");

		} else {
			xmlHttpRequest = new XMLHttpRequest();
		}
		xmlHttpRequest.open("post", "/share/public/getShareBlog.do", true);
		xmlHttpRequest.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xmlHttpRequest.onreadystatechange = ajaxGetBlogCallBack;
		xmlHttpRequest.send("blogKey=" + blogKey);
		xmlHttpRequest.onreadystatechange = ajaxGetBlogCallBack;

	}

	var ajaxGetBlogCallBack = function() {

		if (xmlHttpRequest.readyState == 4) {

			var elementFileList = document.getElementById("fileList");
			var elementBlog = document.getElementById("blogShow");
			var elementBlogList = document.getElementById("blogList");
			var elementCommentAeraDoc = document.getElementById("commentAeraOfDoc");
			var elementCommentAeraBlog = document.getElementById("commentAeraOfBlog");

			elementFileList.style.display = "none";
			elementBlog.style.display = "";
			elementBlogList.style.display = "none";
			elementCommentAeraDoc.style.display = "none";
			elementCommentAeraBlog.style.display = "none";

			var result = xmlHttpRequest.responseText;
			// result = decodeURIComponent(result);这段代码有时候需要
			var json = eval("(" + result + ")");//返回的json的第一组值为当前提交的blog，其他为所有blog
			//发表成功后，隐藏表单局域，显示刚刚发表的blog
			var thisBlog = json[0];//第一个元素是提交的blog

			document.getElementById("thisBlogTitle").innerHTML = thisBlog.titleName;
			document.getElementById("thisBlogDate").innerHTML = "发布时间："
					+ thisBlog.blogDate;
			document.getElementById("thisBlogContent").innerHTML = thisBlog.blogContent;

		}
	};
	function commentThisFile(dockey){
		var elementCommentAera = document.getElementById("commentAeraOfDoc");
		var elementCommentClikButton = document.getElementById("commentDocClikButton");
		elementCommentClikButton.setAttribute("onclick", "commentDocClick('"+dockey+"')");
		elementCommentAera.style.display = "";
		//通过ajax获取该文件的评论
		//不同的浏览器获取对象xmlhttprequest 对象方法不一样
		if (window.ActiveXObject) {

			xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");

		} else {
			xmlHttpRequest = new XMLHttpRequest();
		}
		xmlHttpRequest.open("post", "/share/public/getShareDocComment.do", true);
		xmlHttpRequest.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xmlHttpRequest.onreadystatechange = ajaxShareDocCommentCallBack;
		xmlHttpRequest.send("dockey=" + dockey);
		xmlHttpRequest.onreadystatechange = ajaxShareDocCommentCallBack;
	}
	var ajaxShareDocCommentCallBack = function(){
		if (xmlHttpRequest.readyState == 4) {
			
			var elementCommentAera = document.getElementById("commentAeraOfDoc");
			
			elementCommentAera.style.display = "";
			var result = xmlHttpRequest.responseText;
			
			// result = decodeURIComponent(result);这段代码有时候需要
			var json = eval("(" + result + ")");
		
			//在评论区域插入评论
			var elementComments = document.getElementById("commentsDoc");
			elementComments.innerHTML = "";
			for(var i=0;i<json.length;i++){
				var elementThisComment = document.createElement("div");//单个评论区
				elementThisComment.className="singleComment";
				
				var elementFloor = document.createElement("div");
				elementFloor.innerHTML = "楼层："+(i+1)+'#'; 
				
				var elementDate = document.createElement("div");
				elementDate.innerHTML = "日期："+json[i].date;
				var elementUser = document.createElement("div");
				elementUser.innerHTML = "用户："+json[i].user;
				var elementComment = document.createElement("div");
				
				elementComment.innerHTML = "内容："+json[i].commentText;
				
				elementThisComment.appendChild(elementFloor);
				elementThisComment.appendChild(elementDate);
				elementThisComment.appendChild(elementUser);
				elementThisComment.appendChild(elementComment);
				
				elementComments.appendChild(elementThisComment);
				
			}
		}
	};
	//发布评论
	function commentDocClick(dockey){
		var content = CKEDITOR.instances.contentEdit.getData();
		CKEDITOR.instances.contentEdit.setData(""); 
		content = encodeURIComponent(encodeURIComponent(content));
		
		if(content.length<1){
			alert("内容为空");
		}else{
			//不同的浏览器获取对象xmlhttprequest 对象方法不一样
			if (window.ActiveXObject) {

				xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");

			} else {
				xmlHttpRequest = new XMLHttpRequest();
			}
			xmlHttpRequest.open("post", "/share/public/uploadDocCommentText.do", true);
			xmlHttpRequest.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xmlHttpRequest.onreadystatechange = ajaxSendCommentCallBack;
			xmlHttpRequest.send("content="+content+"&dockey="+dockey);
			xmlHttpRequest.onreadystatechange = ajaxSendCommentCallBack;
		}
		
		
	}
	var ajaxSendCommentCallBack = function(){
		if (xmlHttpRequest.readyState == 4) {

			var elementCommentAera = document.getElementById("commentAeraOfDoc");
			
			elementCommentAera.style.display = "";
			var result = xmlHttpRequest.responseText;
			
			// result = decodeURIComponent(result);这段代码有时候需要
			var json = eval("(" + result + ")");
		
			//在评论区域插入评论
			var elementComments = document.getElementById("commentsDoc");
			elementComments.innerHTML = "";
			for(var i=0;i<json.length;i++){
				var elementThisComment = document.createElement("div");//单个评论区
				elementThisComment.className="singleComment";
				
				var elementFloor = document.createElement("div");
				elementFloor.innerHTML = "楼层："+(i+1)+'#'; 
				
				var elementDate = document.createElement("div");
				elementDate.innerHTML = "日期："+json[i].date;
				var elementUser = document.createElement("div");
				elementUser.innerHTML = "用户："+json[i].user;
				var elementComment = document.createElement("div");
				
				elementComment.innerHTML = "内容："+json[i].commentText;
				
				elementThisComment.appendChild(elementFloor);
				elementThisComment.appendChild(elementDate);
				elementThisComment.appendChild(elementUser);
				elementThisComment.appendChild(elementComment);
				
				elementComments.appendChild(elementThisComment);
				
			}
		}
	};
	
	function commentThisBlog(blogkey){
		var elementCommentAera = document.getElementById("commentAeraOfBlog");
		var elementCommentClikButton = document.getElementById("commentBlogClikButton");
		elementCommentClikButton.setAttribute("onclick", "commentBlogClick('"+blogkey+"')");
		elementCommentAera.style.display = "";
		//通过ajax获取该文件的评论
		//不同的浏览器获取对象xmlhttprequest 对象方法不一样
		if (window.ActiveXObject) {

			xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");

		} else {
			xmlHttpRequest = new XMLHttpRequest();
		}
		xmlHttpRequest.open("post", "/share/public/getBlogCommentText.do", true);
		xmlHttpRequest.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xmlHttpRequest.onreadystatechange = ajaxGetBlogCommentCallBack;
		xmlHttpRequest.send("blogkey=" + blogkey);
		xmlHttpRequest.onreadystatechange = ajaxGetBlogCommentCallBack;
	}
	var ajaxGetBlogCommentCallBack = function(){
		if (xmlHttpRequest.readyState == 4) {
			var elementCommentAera = document.getElementById("commentAeraOfBlog");
			
			elementCommentAera.style.display = "";
			var result = xmlHttpRequest.responseText;
			
			// result = decodeURIComponent(result);这段代码有时候需要
			var json = eval("(" + result + ")");
		
			//在评论区域插入评论
			var elementComments = document.getElementById("commentsBlog");
			elementComments.innerHTML = "";
			for(var i=0;i<json.length;i++){
				var elementThisComment = document.createElement("div");//单个评论区
				elementThisComment.className="singleComment";
				
				var elementFloor = document.createElement("div");
				elementFloor.innerHTML = "楼层："+(i+1)+'#'; 
				
				var elementDate = document.createElement("div");
				elementDate.innerHTML = "日期："+json[i].date;
				var elementUser = document.createElement("div");
				elementUser.innerHTML = "用户："+json[i].user;
				var elementComment = document.createElement("div");
				
				elementComment.innerHTML = "内容："+json[i].commentText;
				
				elementThisComment.appendChild(elementFloor);
				elementThisComment.appendChild(elementDate);
				elementThisComment.appendChild(elementUser);
				elementThisComment.appendChild(elementComment);
				
				elementComments.appendChild(elementThisComment);
				
			}
		}
	};
	function commentBlogClick(blogkey){
		var content = CKEDITOR.instances.contentEdit2.getData();
		CKEDITOR.instances.contentEdit2.setData(""); 
		content = encodeURIComponent(encodeURIComponent(content));
		
		if(content.length<1){
			alert("内容为空");
		}else{
			//不同的浏览器获取对象xmlhttprequest 对象方法不一样
			if (window.ActiveXObject) {

				xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");

			} else {
				xmlHttpRequest = new XMLHttpRequest();
			}
			xmlHttpRequest.open("post", "/share/public/uploadBlogCommentText.do", true);
			xmlHttpRequest.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xmlHttpRequest.onreadystatechange = ajaxGetBlogCommentCallBack;
			xmlHttpRequest.send("content="+content+"&blogkey="+blogkey);
			xmlHttpRequest.onreadystatechange = ajaxGetBlogCommentCallBack;
		}
		
	}
</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分享</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>//css//style.css" type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>//css//share.css" type="text/css" />
<script src="<%=request.getContextPath()%>//ckeditor_full2share/ckeditor.js"></script>
<script src="<%=request.getContextPath()%>//js//jquery-2.2.2.js"></script>
</head>
<body>
	<div class="header" id="header">

		<ul>
			<li><a href="/share/private//main.do">主页</a></li>
			<li><a href="/share/private//file.do">文件</a></li>
			<li><a href="/share/private//blog.do">博文</a></li>
			<li class="selected"><a href="/share/public//share.do">分享</a></li>
			<li><a href="/share/private//logout.do">注销</a></li>
		</ul>
		<div class="showUserName">hello,${userValid.nickName}</div>
	</div>
	<br />
	<!-- 左侧列表 -->
	<div class="left">
		<div class="shareFileLeftList">
			<img src="<%=request.getContextPath()%>//images//tag1.jpg" /> <a
				href="#" onclick="shareFileListOnclick()">分享文件</a>
		</div>
		<div class="shareBlogLeftList">
			<img src="<%=request.getContextPath()%>//images//tag2.jpg" /> <a
				href="#" onclick="shareBlogListOnclick()">分享文章</a>
		</div>
	</div>

	<div class="main">
		<div id="fileList">
			<table id="mainTable" name="fileTable">
				<tr class="tableTile">
					<td class="fasfsaf">序数</td>
					<td class="fasfsaf">文件名</td>
					<td class="fasfsaf">文件大小</td>
					<td class="fasfsaf">上传日期</td>
					<td class="fasfsaf">分享用户</td>
					<td class="fasfsaf">操作</td>


				</tr>
				<c:forEach var="doc" items="${shareDocumentsList}"
					varStatus="status">
					<tr id="main${status.index + 1}" name="${status.index + 1}">
						<td class="fasfsaf">${status.index + 1}
						<td />

						<td name="dawad" class="td_fileName" title="${doc.name}"><img
							alt="" height="20" width="20"
							src="<%=request.getContextPath()%>//images//${doc.icon}" /> <a
							name="docNameLink" href="${doc.url}">${doc.name}</a>
						<td />

						<td class="fasfsaf">${doc.size}
						<td />

						<td class="fasfsaf">${doc.date}
						<td />

						<td class="fasfsaf">${doc.author}
						<td />

						<td class="fasfsaf"><a href="#"
							onclick="commentThisFile('${doc.dockey}')">评论（${doc.commentCount}）</a>
						<td />
					<tr />
				</c:forEach>
			</table>
		</div>
		<script>
			
		</script>
		<div id="blogList" style="display: none">
			<table id="blogTable" name="blogTable">
				<tr class="tableTile">
					<td class="fasfsaf">序数</td>
					<td class="fasfsaf">文件名</td>
					<td class="fasfsaf">上传日期</td>
					<td class="fasfsaf">分享用户</td>
					<td class="fasfsaf">类别</td>
					<td class="fasfsaf">操作</td>


				</tr>
				<c:forEach var="blog" items="${shareBlogsList}" varStatus="status">
					<tr id="main${status.index + 1}" name="${status.index + 1}">
						<td class="fasfsaf">${status.index + 1}
						<td />

						<td class="td_fileName" title="${blog.name}"><a
							name="docNameLink" href="#"
							onclick="getBlogClick('${blog.blogkey}')">${blog.name}</a>
						<td />


						<td name="docDate" class="fasfsaf">${blog.date}
						<td />

						<td class="fasfsaf">${blog.author}
						<td />

						<td class="fasfsaf">${blog.className}
						<td />
						<td class="fasfsaf"><a href="#"
							onclick="commentThisBlog('${blog.blogkey}')">评论（${blog.commentCount}）</a>
						<td />
					<tr />
				</c:forEach>
			</table>
		</div>
		<div id="blogShow" style="display: none">
			<div id="showBlogChild">
				<div id="thisBlogTitle"></div>
				<div id="thisBlogDate"></div>
				<div id="thisBlogContent"></div>
			</div>
		</div>
	</div>
	<div class="right">
		<div id="commentAeraOfDoc" style="display:none">
			
			<div class="content" id="content">
				
				<textarea id="contentEdit" name="contentEdit"></textarea>
				<script>
					//CKEDITOR.replace("contentEdit");
					CKEDITOR.replace('contentEdit', {
						toolbar : [
								[ 'Bold', 'Italic', 'Underline',
										'Subscript', 'Superscript' ],
								[ 'Link', 'Anchor' ,'Image','Smiley', 'SpecialChar'],
								'/',
								['FontSize','TextColor', 'BGColor' ]
								]});
				</script>
				<a href="#" id="commentDocClikButton">发布</a>
			</div>
			
			<div id="commentsDoc"></div>
		</div>
		<div id="commentAeraOfBlog" style="display:none">
			
			<div class="content" id="content">
				
				<textarea id="contentEdit2" name="contentEdit2"></textarea>
				<script>
					//CKEDITOR.replace("contentEdit");
					CKEDITOR.replace('contentEdit2', {
						toolbar : [
								[ 'Bold', 'Italic', 'Underline',
										'Subscript', 'Superscript' ],
								[ 'Link', 'Anchor' ,'Image','Smiley', 'SpecialChar'],
								'/',
								['FontSize','TextColor', 'BGColor' ]
								]});
				</script>
				<a href="#" id="commentBlogClikButton">发布</a>
			</div>
			
			<div id="commentsBlog"></div>
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


</body>
<script type="text/javascript">
	function contanctMe() {
		alert("邮箱：wuchaodzxx@126.com\nQQ:1061616449");
	}

	(function deleteTd() {

		var tdList = document.getElementsByTagName("td");

		for (var i = 0; i < tdList.length; i++) {
			if (tdList[i].className.length <= 0) {
				tdList[i].parentNode.removeChild(tdList[i]);
			}

		}
	})();
	
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
</script>
</html>