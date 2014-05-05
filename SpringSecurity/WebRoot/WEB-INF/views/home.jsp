<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="common/header.jsp">
	<jsp:param value="Home" name="pageTitle"/>
</jsp:include>
<h1>欢迎来到宠物用品店</h1>
<p>
	我们为你精心准备很多用品给你的心爱的宠物
</p>
<ul>
	<li><a href="<%=request.getContextPath()%>/commoditiez/list.do?type=dog">Dogs</a></li>
	<li><a href="<%=request.getContextPath()%>/commoditiez/list.do?type=cat">Cats</a></li>
</ul>

<h2></h2>
<ul>
	<c:forEach var="category" items="${categories}">
		<li><a href="category.do?id=${category.id}">${category.name}</a></li>
	</c:forEach>
</ul>
<jsp:include page="common/footer.jsp"/>

