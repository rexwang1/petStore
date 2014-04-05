<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="common/header.jsp">
	<jsp:param value="Login" name="pageTitle"/>
</jsp:include>
<h1>请登陆你的帐号</h1>
<p>
	请填写好下面信息
</p>

<div class="error${param.error==true?'':'hide'}">
	登陆失败<br/>
	${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message =='Bad credentials' ? '无效用户' : ''}<br/>
	
</div>
<br/>
<form action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
	<label for="j_username">用户名</label>:
	<input id="j_username" name="j_username" size="20" maxlength="50" type="text" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}"/>
	<br/>
	<%-- 默认的remember-me--%>
	<input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox"/>
	<label for="_spring_security_remember_me">记住密码?</label>
	<br/>
	<%-- 
	<input id="_remember_me" name="_remember_me" type="checkbox"/>
	<label for="_remember_me">Remember Me?</label>
	<br/>
	--%>
	<label for="j_password">密码</label>:
	<input id="j_password" name="j_password" size="20" maxlength="50" type="password"/>
	<br/>
	<input type="submit" value="登陆"/>
</form>
<jsp:include page="common/footer.jsp"/>