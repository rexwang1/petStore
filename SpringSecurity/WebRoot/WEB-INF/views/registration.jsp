<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!--  
	<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
-->

<jsp:include page="common/header.jsp">
	<jsp:param value="Register" name="pageTitle"/>
</jsp:include>
<h1>User Register</h1>
<p>
	请填写好以下信息
</p>
<form action="registration.do" method="post">
	<label for="username">用户名</label>:
	<input id="username" name="username" size="20" maxlength="50" type="text"/>
	<font id="checkUsername">*</font>
	<br />
	<label for="password">密码</label>:
	<input id="password" name="password" size="20" maxlength="50" type="password"/>
	<font id="checkPassword">*</font>
	<br />
	<label for="email">邮箱</label>:
	<input id="email" name="email" size="20" maxlength="50" type="email"/>
	<br />
	<input type="submit" value="注册" id="registBtn"/>
</form>


<jsp:include page="common/footer.jsp"/>