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
<title>登陆</title>
<style type="text/css">.errorClass{color:red}</style>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>//css//login.css" type="text/css" />
</head>
<body>
	<form:form  modelAttribute="user" action="/share/loginForm.do" class="formClass" method="POST" accept-charset="utf-8">
		<table>
			<tr><span style="color:red">${error}</span></tr>
			<tr>
				<td>用户名：</td>
				<td>
					<form:input path="name"/>
					<form:errors path="name" cssClass="errorClass"/>
					
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
				<td>
					<input type="submit" value="提交" class="sendButton"></input>
					<input type="button" value="注册" class="registButton" onclick="window.location.href='/share/regist.do'"/> 
				</td>
			</tr>
		</table>
		
	</form:form>
</body>
</html>