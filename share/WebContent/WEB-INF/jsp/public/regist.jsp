<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<style type="text/css">.errorClass{color:red}</style>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>//css//regist.css" type="text/css" />
</head>
<body>
	<form:form  modelAttribute="user"  action="/share/registForm.do" method="POST" accept-charset="utf-8" class="formClass">
		<table>
			<tr><span style="color:red">${userIsExsit}</span></tr>
			<tr>
				<td>用户名：</td>
				<td>
					<form:input path="name"/>
					<form:errors path="name" cssClass="errorClass"/>
					
				</td>
			</tr>
			<tr>
				<td>昵称：</td>
				<td>
					<form:input path="nickName"/>
					<form:errors path="nickName" cssClass="errorClass"/>
				</td>
			</tr>
			<tr>
				<td>年龄：</td>
				<td>
					<form:input path="age"/>
					<form:errors path="age" cssClass="errorClass"/>
					
				</td>
			</tr>
			<tr>
				<td>邮箱：</td>
				<td>
					<form:input path="email"/>
					<form:errors path="email" cssClass="errorClass"/>
					
				</td>
			</tr>
			<tr>
				<td>密码：</td>
				<td>
					<form:input type="password" path="password"/>
					<form:errors path="password" cssClass="errorClass"/>
					
				</td>
			</tr>
			<tr>
				
				<td>
					
				</td>
				<td class="tableButton">
					<input type="submit" value="提交" class="sendButton"></input>&nbsp;&nbsp;
					<input type="button" value="登陆" class="loginButton" onclick="window.location.href='/share/login.do'"/> 
				</td>
				
			</tr>
		</table>
		
	</form:form>
</body>
</html>