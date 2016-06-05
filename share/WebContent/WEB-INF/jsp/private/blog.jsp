<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博文</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>//css//style.css" type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>//css//blog.css" type="text/css" />
<script src="<%=request.getContextPath()%>//ckeditor_full/ckeditor.js"></script>
<script src="<%=request.getContextPath()%>//js/jquery-1.12.3.min.js"></script>
<script>
    var currentBlogKey = null;//全局变量，记录当前显示的blog
    var currentBlogContent = null;//全局变量，记录当前显示的blog
    var currentBlogTitle = null;//全局变量，记录当前显示的blog
	var xmlHttpRequest;
	function sendBlog() {
		var titleName = document.getElementById("blogTitle").value;
		var content = CKEDITOR.instances.contentEdit.getData();
		var className = document.getElementById("blogClasses").value;
		
		if(titleName.length>=1){
			content = encodeURIComponent(encodeURIComponent(content));

			//不同的浏览器获取对象xmlhttprequest 对象方法不一样
			if (window.ActiveXObject) {

				xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");

			} else {
				xmlHttpRequest = new XMLHttpRequest();
			}
			xmlHttpRequest.open("post", "/share/private/uploadBlog.do", true);
			xmlHttpRequest.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xmlHttpRequest.onreadystatechange = ajaxSendBlogCallBack;
			xmlHttpRequest.send("content="+content+"&titleName="+titleName+"&className="+className);
			xmlHttpRequest.onreadystatechange = ajaxSendBlogCallBack;

		}else{
			alert("请输入文章标题！");
		}
		
	}
	var ajaxSendBlogCallBack = function() {
		
		if (xmlHttpRequest.readyState == 4) {
			 var result = xmlHttpRequest.responseText;
			// result = decodeURIComponent(result);这段代码有时候需要
			 var json = eval("(" + result + ")");//返回的json的第一组值为当前提交的blog，其他为所有blog
			 //发表成功后，隐藏表单局域，显示刚刚发表的blog
			 var thisBlog = json[0];//第一个元素是提交的blog
			 
		
			 
			 currentBlogContent = thisBlog.blogContent;//全局变量，记录当前显示的blog
			 currentBlogTitle = thisBlog.titleName;//全局变量，记录当前显示的blog
			 currentBlogKey = thisBlog.blogkey;//全局变量，记录当前显示的blog
			 
			 
			 document.getElementById("form").style.display="none";
			 document.getElementById("showBlog").style.display="";
			 document.getElementById("thisBlogTitle").innerHTML =thisBlog.titleName;
			 document.getElementById("thisBlogDate").innerHTML ="发布时间："+thisBlog.blogDate;
			 document.getElementById("thisBlogContent").innerHTML =thisBlog.blogContent;
			// 根据当前文章是否为分享状态，显示对应按钮
			 var elementShareButton = document.getElementById("shareBlog");
			 var elementCancelShareButton = document.getElementById("cancelShareBlog");
			
			 if(thisBlog.blogIsShare=="yes"){
				 elementShareButton.style.display="none";
				 elementCancelShareButton.style.display="";
			 }else{
				 elementShareButton.style.display="";
				 elementCancelShareButton.style.display="none";
			 }
			 
			  //左侧列表显示
			 var classNames = json[1].classNames;//第二个元素是当前用户的所有className;
			 var element = document.getElementById("blogList");
			 element.innerHTML = "";
			 var index = 0;
			 var index2 = 0;
			 for(var i=0;i<classNames.length;i++){
				 
				 var tagNum = (index2%5)+1;
				 index2 = index2+1 ;
				 	//创建当前className
				 
				 	var classNameElement = document.createElement("div");
				 	classNameElement.id = classNames[i]; 
				 	classNameElement.className = "className"; 
				 	
				 	
			 				
				 	var classNameElementA = document.createElement("a");
				 	classNameElementA.href="#";
				 	classNameElementA.setAttribute("onclick","selectClassNameHide("+"'"+classNames[i]+"Child"+"'"+")");
				 	classNameElementA.innerHTML = "<img src='<%=request.getContextPath()%>//images//tag"+tagNum+".jpg'/>"+classNames[i]+"<br/>";
				 	classNameElement.appendChild(classNameElementA);
				 	
				 	var classNameChildElement = document.createElement("div");
				 	classNameChildElement.id = classNames[i]+"Child"; 
				 	classNameChildElement.className = "leftClassNameChild"; 
				 	classNameChildElement.style.display="none";
				 	
				 	
				 	var index = 0;
			 		for(var j=2;j<json.length;j++){
			 			
			 			if(classNames[i]==json[j].blogClassName){
			 				index = index+1;
			 				//创建div，包含文字链接和删除图标
			 				var blogLinkElement = document.createElement("div");
			 				blogLinkElement.id = classNames[i]+"Child"+index;
			 				blogLinkElement.setAttribute("onmouseover","selectBlogLinkElementDisplay("+"'"+"image"+json[j].blogKey+"'"+")");
			 				blogLinkElement.setAttribute("onmouseout","selectBlogLinkElementHide("+"'"+"image"+json[j].blogKey+"'"+")");
			 				//在这里为每个博文创建链接
			 				var blogElement = document.createElement("a");
			 				blogElement.id = json[j].blogKey;
			 				blogElement.className = "blogLink";
			 				blogElement.href = "#";
			 				blogElement.innerHTML = getStr(json[j].titleName);
			 				blogElement.title = json[j].titleName;
			 				blogElement.setAttribute("onclick","selectBlog("+"'"+json[j].blogKey+"'"+")");
			 				//为每个blog添加删除按钮
			 				var elementImage = document.createElement("img");
			 				elementImage.id = "image"+json[j].blogKey;
			 				//elementImage.setAttribute("id","image"+json[j].blogKey);
			 				elementImage.setAttribute("src","<%=request.getContextPath()%>//images//delete2.gif");
			 				elementImage.setAttribute("onclick","deleteBlog("+"'"+json[j].blogKey+"'"+")");
			 				elementImage.style.width = "12px";
			 				elementImage.style.height = "12px";
			 				elementImage.style.display = "none";
			 				
			 				blogLinkElement.appendChild(blogElement);
			 				
			 				blogLinkElement.appendChild(elementImage);
			 				
			 				blogLinkElement.appendChild(document.createElement("br"));
			 				
			 				classNameChildElement.appendChild(blogLinkElement);
			 				
			 			}
			 		}
			 		
			 		classNameElement.appendChild(classNameChildElement);
			 		element.appendChild(classNameElement);
			 		
			 }
			 
		
		}
	};
	
 function selectBlog(blogKey){
	//不同的浏览器获取对象xmlhttprequest 对象方法不一样
		if (window.ActiveXObject) {

			xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");

		} else {
			xmlHttpRequest = new XMLHttpRequest();
		}
		xmlHttpRequest.open("post", "/share/private/getBlog.do", true);
		xmlHttpRequest.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xmlHttpRequest.onreadystatechange = ajaxGetBlogCallBack;
		xmlHttpRequest.send("blogKey="+blogKey);
		xmlHttpRequest.onreadystatechange = ajaxGetBlogCallBack;
		
		currentBlogKey = blogKey;//全局变量，记录当前显示的blog

	
}

var ajaxGetBlogCallBack = function() {
		
		if (xmlHttpRequest.readyState == 4) {
			 var result = xmlHttpRequest.responseText;
			
			// result = decodeURIComponent(result);这段代码有时候需要
			 var json = eval("(" + result + ")");//返回的json的第一组值为当前提交的blog，其他为所有blog
			 //发表成功后，隐藏表单局域，显示刚刚发表的blog
			 var thisBlog = json[0];//第一个元素是提交的blog
			 document.getElementById("form").style.display="none";
			 document.getElementById("showBlog").style.display="";
			 document.getElementById("thisBlogTitle").innerHTML =thisBlog.titleName;
			 document.getElementById("thisBlogDate").innerHTML ="发布时间："+thisBlog.blogDate;
			 document.getElementById("thisBlogContent").innerHTML =thisBlog.blogContent;
			 
			// 根据当前文章是否为分享状态，显示对应按钮
			 var elementShareButton = document.getElementById("shareBlog");
			 var elementCancelShareButton = document.getElementById("cancelShareBlog");
			
			 if(thisBlog.blogIsShare=="yes"){
				 elementShareButton.style.display="none";
				 elementCancelShareButton.style.display="";
			 }else{
				 elementShareButton.style.display="";
				 elementCancelShareButton.style.display="none";
			 }
			 
			
			 currentBlogContent = thisBlog.blogContent;//全局变量，记录当前显示的blog
			 currentBlogTitle = thisBlog.titleName;//全局变量，记录当前显示的blog
			 currentBlogKey = thisBlog.blogkey;
			 
		}
	};
	
function onloadInit(){
	//不同的浏览器获取对象xmlhttprequest 对象方法不一样
	if (window.ActiveXObject) {

		xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");

	} else {
		xmlHttpRequest = new XMLHttpRequest();
	}
	xmlHttpRequest.open("post", "/share/private/blogInit.do", true);
	xmlHttpRequest.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	xmlHttpRequest.onreadystatechange = ajaxBlogInitCallBack;
	xmlHttpRequest.send(null);
	xmlHttpRequest.onreadystatechange = ajaxBlogInitCallBack;
	
}
var ajaxBlogInitCallBack = function(flag) {
	
	if (xmlHttpRequest.readyState == 4) {
		
			var result = xmlHttpRequest.responseText;
			// result = decodeURIComponent(result);//这段代码有时候需要

			var json = eval("(" + result + ")");
		
			
			 
			 //左侧列表显示
			 var classNames = json[0].classNames;//第二个元素是当前用户的所有className;
			 var element = document.getElementById("blogList");
			 element.innerHTML = "";
			 var index = 0;
			 var index2 = 0;
			 for(var i=0;i<classNames.length;i++){
				 var tagNum = (index2%5)+1;
				 index2 = index2+1 ;
				 	//创建当前className
				 
				 	var classNameElement = document.createElement("div");
					 	classNameElement.id = classNames[i]; 
					 	classNameElement.className = "className"; 
					 	var classNameElementA = document.createElement("a");
					 	classNameElementA.href="#";
					 	classNameElementA.setAttribute("onclick","selectClassNameHide("+"'"+classNames[i]+"Child"+"'"+")");
					 	classNameElementA.innerHTML = "<img src='<%=request.getContextPath()%>//images//tag"+tagNum+".jpg'/>"+classNames[i]+"<br/>";
					 	classNameElement.appendChild(classNameElementA);
				 	
				 	
				 	
				 	var classNameChildElement = document.createElement("div");
				 	classNameChildElement.id = classNames[i]+"Child";
				 	classNameChildElement.className = "leftClassNameChild"; 
				 	classNameChildElement.style.display="none";
				 	
				 	
			 		for(var j=1;j<json.length;j++){
			 			if(classNames[i]==json[j].blogClassName){
			 				index = index+1;
			 				//创建div，包含文字链接和删除图标
			 				var blogLinkElement = document.createElement("div");
			 				blogLinkElement.id = classNames[i]+"Child"+index;
			 				blogLinkElement.setAttribute("onmouseover","selectBlogLinkElementDisplay("+"'"+"image"+json[j].blogKey+"'"+")");
			 				blogLinkElement.setAttribute("onmouseout","selectBlogLinkElementHide("+"'"+"image"+json[j].blogKey+"'"+")");
			 				//在这里为每个博文创建链接
			 				var blogElement = document.createElement("a");
			 				blogElement.id = json[j].blogKey;
			 				blogElement.className = "blogLink";
			 				blogElement.href = "#";
			 				blogElement.innerHTML = getStr(json[j].titleName);
			 				blogElement.title = json[j].titleName;
			 				blogElement.setAttribute("onclick","selectBlog("+"'"+json[j].blogKey+"'"+")");
			 				//为每个blog添加删除按钮
			 				var elementImage = document.createElement("img");
			 				elementImage.id = "image"+json[j].blogKey;
			 				elementImage.setAttribute("src","<%=request.getContextPath()%>//images//delete2.gif");
							elementImage.setAttribute("onclick", "deleteBlog("
									+ "'" + json[j].blogKey + "'" + ")");
							elementImage.style.width = "12px";
							elementImage.style.height = "12px";
							elementImage.style.display = "none";

							blogLinkElement.appendChild(blogElement);
							blogLinkElement.appendChild(elementImage);

							blogLinkElement.appendChild(document
									.createElement("br"));

							classNameChildElement.appendChild(blogLinkElement);

						}
					}
					classNameElement.appendChild(classNameChildElement);
					element.appendChild(classNameElement);
				}

			}
		

	};

	function selectClassNameHide(elementId) {

		var element = document.getElementById(elementId);

		if (element.style.display == "none") {
			element.style.display = "";
		} else {
			element.style.display = "none"
		}
	}

	function selectBlogLinkElementHide(elementId) {
		var element = document.getElementById(elementId);

		element.style.display = "none";

	}
	function selectBlogLinkElementDisplay(elementId) {
		var element = document.getElementById(elementId);

		element.style.display = "";

	}
	function deleteBlog(blogKey) {
		var r=confirm("确认要删除这篇文章?");
		if(r){
			//不同的浏览器获取对象xmlhttprequest 对象方法不一样
			if (window.ActiveXObject) {

				xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");

			} else {
				xmlHttpRequest = new XMLHttpRequest();
			}
			xmlHttpRequest.open("post", "/share/private/deleteBlog.do", true);
			xmlHttpRequest.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xmlHttpRequest.onreadystatechange = ajaxBlogInitCallBack;
			xmlHttpRequest.send("blogKey=" + blogKey);
			xmlHttpRequest.onreadystatechange = ajaxBlogInitCallBack;
			
			publishBlog();
		}
		
		
	}
	//使用正则表达式计算字符串的字节长度
	var lenReg = function(str){

	    return str.replace(/[^\x00-\xFF]/g,'**').length;

	};
	var getStr = function(str){
		var len =0;
		var strR ="" ;
		for(var i=0;i<str.length;i++){
			len=len+lenReg(str[i]);
			strR = strR+str[i];
			if(len>=16){
				return strR+"……"
		}
		}
		return str;
	}
</script>
<script>
	var xmlHttpRequest2;
	function shareBlog(){
		var blogId = currentBlogKey ;
		
		xmlHttpRequest2 = new XMLHttpRequest();
		xmlHttpRequest.addEventListener("load", ajaxShareGetBlogCallBack, false);
		xmlHttpRequest.addEventListener("error", uploadFailed, false);
		xmlHttpRequest.open("GET", "/share/private/shareBlog.do?blogkey="+encodeURIComponent(blogId));//修改成自己的接口
		xmlHttpRequest.send(null);
	}

	function cancelShareBlog(){
		var blogId = currentBlogKey ;
	
		xmlHttpRequest2 = new XMLHttpRequest();
		xmlHttpRequest.addEventListener("load", ajaxShareGetBlogCallBack, false);
		xmlHttpRequest.addEventListener("error", uploadFailed, false);
		xmlHttpRequest.open("GET", "/share/private/cancelShareBlog.do?blogkey="+encodeURIComponent(blogId));//修改成自己的接口
		xmlHttpRequest.send(null);
	}
var ajaxShareGetBlogCallBack = function() {
		
		if (xmlHttpRequest.readyState == 4) {
			 var result = xmlHttpRequest2.responseText;
			
			// result = decodeURIComponent(result);这段代码有时候需要
			 var json = eval("(" + result + ")");//返回的json的第一组值为当前提交的blog，其他为所有blog
			 //发表成功后，隐藏表单局域，显示刚刚发表的blog
			 var thisBlog = json[0];//第一个元素是提交的blog
			 document.getElementById("form").style.display="none";
			 document.getElementById("showBlog").style.display="";
			 document.getElementById("thisBlogTitle").innerHTML =thisBlog.titleName;
			 document.getElementById("thisBlogDate").innerHTML ="发布时间："+thisBlog.blogDate;
			 document.getElementById("thisBlogContent").innerHTML =thisBlog.blogContent;
			 
			// 根据当前文章是否为分享状态，显示对应按钮
			 var elementShareButton = document.getElementById("shareBlog");
			 var elementCancelShareButton = document.getElementById("cancelShareBlog");
			 alert(thisBlog.blogIsShare+"===422");
			 if(thisBlog.blogIsShare=="yes"){
				 elementShareButton.style.display="none";
				 elementCancelShareButton.style.display="";
			 }else{
				 elementShareButton.style.display="";
				 elementCancelShareButton.style.display="none";
			 }
			 
			
			 currentBlogContent = thisBlog.blogContent;//全局变量，记录当前显示的blog
			 currentBlogTitle = thisBlog.titleName;//全局变量，记录当前显示的blog
			 currentBlogKey = thisBlog.blogkey;
			 
		}
	};

	function addBlogClass(className) {
		var len = lenReg(className);
		if(len<=20){
			var xmlHttpRequest = new XMLHttpRequest();
			xmlHttpRequest.addEventListener("load", uploadComplete, false);
			xmlHttpRequest.addEventListener("error", uploadFailed, false);
			xmlHttpRequest.open("GET", "/share/private/addBlogClass.do?className="
					+ encodeURIComponent(className));//修改成自己的接口
			xmlHttpRequest.send(null);
		}else{
			alert("名称长度限制为20个字节，其中每个汉字占两个字节，字母占一个字节。");
		}
		
	}
	function deleteBlogClass(className){
		var xhr = new XMLHttpRequest();
		xmlHttpRequest.addEventListener("load", uploadComplete, false);
		xmlHttpRequest.addEventListener("error", uploadFailed, false);
		xmlHttpRequest.open("GET", "/share/private/deleteBlogClass.do?className="
				+ encodeURIComponent(className));//修改成自己的接口
				xmlHttpRequest.send(null);
	}
	
	function addBlogClassSeletct() {
		var className = prompt("分组名称", "请输入新的分组名称");
		if (className != null) {
			if(className!="默认分组"){
				addBlogClass(className);
			}else{
				alert("你无法删除默认分组！");
			}
			
		}

	}
	function deleteBlogClassSeletct(){
		var className = prompt("请输入需要删除的分组名", "分组名称");
		if (className != null) {
			deleteBlogClass(className);
		}
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
	function publishBlog() {
		document.getElementById("blogTitle").value = "请输入标题";
		document.getElementById("form").style.display = "";
		document.getElementById("showBlog").style.display = "none";
		document.getElementById("sendBlogA").style.display = "";//显示发送按钮，因为在修改页面会隐藏该按钮
		document.getElementById("sendReEditBlogA").style.display = "none";//隐藏该发送按钮，该发送按钮用于发送修改文章
		CKEDITOR.instances.contentEdit.setData(""); //contentEdit是textarea的id值
	}
    var currentBlogKey = null;//全局变量，记录当前显示的blog
    var currentBlogContent = null;//全局变量，记录当前显示的blog
    var currentBlogTitle = null;//全局变量，记录当前显示的blog
	function reEditBlog(){
		//alert("currentBlogKey:"+currentBlogKey);
		//alert("currentBlogContent:"+currentBlogContent);
		//alert("currentBlogTitle:"+currentBlogTitle);
		document.getElementById("blogTitle").value = currentBlogTitle;
		document.getElementById("form").style.display = "";
		document.getElementById("showBlog").style.display = "none";
		
		document.getElementById("sendBlogA").style.display = "none";//隐藏发送按钮
		document.getElementById("sendReEditBlogA").style.display = "";//显示该发送按钮，该发送按钮用于发送修改文章
		
		CKEDITOR.instances.contentEdit.setData(currentBlogContent); //contentEdit是textarea的id值
		
		
		
	}
    
	function sendReEditBlog(){
		var titleName = document.getElementById("blogTitle").value;
		var content = CKEDITOR.instances.contentEdit.getData();
		var className = document.getElementById("blogClasses").value;
		
		content = encodeURIComponent(encodeURIComponent(content));

		//不同的浏览器获取对象xmlhttprequest 对象方法不一样
		if (window.ActiveXObject) {

			xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");

		} else {
			xmlHttpRequest = new XMLHttpRequest();
		}
		xmlHttpRequest.open("post", "/share/private/uploadReEditBlog.do", true);
		xmlHttpRequest.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xmlHttpRequest.onreadystatechange = ajaxSendBlogCallBack;
		xmlHttpRequest.send("content="+content+"&titleName="+titleName+"&className="+className+"&currentBlogKey="+currentBlogKey);
		xmlHttpRequest.onreadystatechange = ajaxSendBlogCallBack;
	}
</script>
</head>
<body>
	<div class="header" id="header">
		<ul>
			<li><a href="/share/private//main.do">主页</a></li>
			<li><a href="/share/private//file.do">文件</a></li>
			<li class="selected"><a href="/share/private//blog.do">博文</a></li>
			<li><a href="/share/public//share.do" >分享</a></li>
			<li><a href="/share/private//logout.do">注销</a></li>
		</ul>
		<div class="showUserName">hello,${userValid.nickName}</div>
	</div>
	<br />
	<!-- 左侧列表 -->
	<div class="left">
		
		<div id="blogList"></div>
	</div>

	<div class="main">
		<div id="showBlog" class="showBlog" style="display: none">
			<div id="publishBlogDiv">
				<a href="#" onclick="publishBlog()">发表</a>
				<a href="#" onclick="reEditBlog()">修改</a>
				
				
				<a href="#" onclick="cancelShareBlog()" id="cancelShareBlog" style="display:none">取消分享</a>
				<a href="#" onclick="shareBlog()" id="shareBlog" style="display:none">分享</a>

				
				
			</div>
				
			
			<div id="showBlogChild">
				<div id="thisBlogTitle"></div>
				<div id="thisBlogDate"></div>
				<div id="thisBlogContent"></div>
			</div>
			
		</div>
		<form id="form">
			<div class="titleDiv" id="titleDiv">
				<div class="row">
					<!-- 下面为选择分类 -->
					<select class="blogClasses" id="blogClasses">
						<c:forEach var="blogClasses" items="${blogClassesList}"
							varStatus="status">
							<option>${blogClasses.className}</option>
						</c:forEach>
					</select>
					<!-- 下面为添加分类 -->
					<a onclick="addBlogClassSeletct()" class="addBlogClass" href="#">添加分类</a>
					<a onclick="deleteBlogClassSeletct()" class="addBlogClass" href="#">删除分类</a>

				</div>
				文章标题 <input type="text" id="blogTitle"> <a id="sendBlogA"
					onclick="sendBlog()" href="#">提交文章</a>
					<a id="sendReEditBlogA"  onclick="sendReEditBlog()" style="display:none" href="#">提交文章</a>
					

			</div>
			<div class="content" id="content">
				<textarea id="contentEdit" name="contentEdit"></textarea>
			</div>


		</form>



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
		// Replace the <textarea id="editor1"> with a CKEditor
		// instance, using default configuration.
		CKEDITOR.replace("contentEdit");
		onloadInit();
	</script>
	<script type="text/javascript">
	function contanctMe(){
		alert("邮箱：wuchaodzxx@126.com\nQQ:1061616449");
	}
	</script>
</body>
</html>