<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib uri="my-taglib" prefix="paging" %>
<jsp:include page="../common/header.jsp">
	<jsp:param value="Home" name="pageTitle"/>
</jsp:include>

<h1></h1>
<form:form action="" method="post">
	<label for="brand">品牌</label>:
	<form:input path="brand" id="brand"/>
	<br/>
	
	<label for="name">名字</label>:
	<form:input path="name" id="name"/>
	<br/>
	
	<label for="factory">生产厂家</label>:
	<form:input path="factory" id="factory"/>
	<br/>
	
	<label for="type">类型</label>:
	<form:select path="type" id="type"> 
		<form:option value="-" label="--请选择--"/>
		<form:options items="${typeList}"/>
	</form:select>
	<br/>
	<!-- 设置为当天
	<label for="purchaseDate">进货时间</label>:
	<form:input path="purchaseDate" id="purchaseDate"/>
	<br/>
	 -->
	<label for="suit">适用范围</label>:
	<form:textarea path="suit" id="suit"/>
	<br/>
	
	
	<label for="effect">功效</label>:
	<form:textarea path="effect" id="effect"/>
	<br/>
	
	
	<label for="material">原料</label>:
	<form:textarea path="material" id="material"/>
	<br/>
	
	<input type="submit" value="保存"/>
</form:form>

<jsp:include page="../common/footer.jsp"/>