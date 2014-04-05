<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../common/header.jsp">
	<jsp:param value="User Index" name="pageTitle"/>
</jsp:include>
<h1>个人中心</h1>
<ul>
	<li><a href="<%=request.getContextPath()%>/userInfo/updateInfo.do?username=<%=request.getUserPrincipal().getName()%>">完善个人信息</a></li>
</ul>
<ul>
	<li><a href="<%=request.getContextPath()%>/account/changePassword.do">修改密码</a></li>
</ul>
  <jsp:include page="../common/footer.jsp"/>