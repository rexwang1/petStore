<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
 <%@ taglib uri="my-taglib" prefix="paging" %>
<jsp:include page="../common/header.jsp">
	<jsp:param value="Home" name="pageTitle"/>
</jsp:include>

<h1></h1>
<form action="" method="post">
	<label for="createDate">生产日期</label>:
	<input type="text" name="createDate"/>
	<br/>
	<label for="expiryDate">过期时间</label>:
	<input type="text" name="expiryDate"/>
	<br/>
	
	<label for="price">价格</label>:
	<input type="text" name="price"/>
	<br/>
	
	<label for="standard">规格</label>:
	<input type="text" name="standard"/>
	<br/>
	
	<label for="num">数量</label>:
	<input type="text" name="num"/> <br/>
	
	<input type="submit" value="保存"/>
</form>

<jsp:include page="../common/footer.jsp"/>