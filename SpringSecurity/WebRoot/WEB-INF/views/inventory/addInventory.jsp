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
<form action="<%=request.getContextPath()%>/inventory/addInventory.do" method="post">
	<label for="brand">品牌</label>:
	<input name="brand" id="brand"/>
	<br/>
	
	<label for="name">名字</label>:
	<input  name="name" id="name"/>
	<br/>
	
	<label for="factory">生产厂家</label>:
	<input name="factory" id="factory"/>
	<br/>
	
	<label for="type">类型</label>:
	
	<select name="type" id="type"> 
		<option value="dog">狗狗用品</option>
		<option value="cat">猫猫用品</option>
		<option value="aquarium">水族用品</option>
	</select>
	<br/>
	
	<label for="amount">数量</label>:
	<input type="text" name="amount"/> <br/>
	
	<label for="suit">适用范围</label>:
	<textarea name="suit" id="suit"></textarea>
	<br/>
	
	<label for="effect">功效</label>:
	<textarea name="effect" id="effect"></textarea>
	<br/>
	
	
	<label for="material">原料</label>:
	<textarea name="material" id="material"></textarea>
	<br/>
	
	<input type="submit" value="保存"/>
</form>

<jsp:include page="../common/footer.jsp"/>