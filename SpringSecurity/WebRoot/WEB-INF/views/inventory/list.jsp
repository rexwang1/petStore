<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
 <%@ taglib uri="my-taglib" prefix="paging" %>
<jsp:include page="../common/header.jsp">
	<jsp:param value="Home" name="pageTitle"/>
</jsp:include>
<h1>欢迎来到宠物用品店</h1>
<p>
	我们为你精心准备很多用品给你的心爱的宠物
</p>
<form action="/inventory/list.do" method="post">
	<table>  
	<thead>
	<tr>
	  
    <td>编号</td>  
    <td>品牌</td> 
    <td>商品名</td> 
    <td>厂家</td> 
    <td>类型</td>
    <td>适用范围</td>
    <td>原料</td>
    <td></td>
    <td></td>
	</tr>
	</thead>
	<tbody>
	
<c:forEach items="${rf.items}" var="inventory">  
<tr>  
    <td><c:out value="${inventory.id}"/></td>  
    <td><c:out value="${inventory.brand}"/></td>  
    <td><c:out value="${inventory.name}"/></td>
    <td><c:out value="${inventory.factory}"/></td> 
    <td><c:out value="${inventory.type}"/></td>
    <td><c:out value="${inventory.suit}"/></td>
    <td><c:out value="${inventory.material}"/></td>
    <!-- 详情主要显示原料，连接商品库表显示库存和进货日期-->
    <td>
    	<c:url value="<%=request.getContextPath()%>/commodities/detail.do?id=${inventory.id}">
    	详情
    	</c:url>
    </td>  
    <td>
    	<c:url value="<%=request.getContextPath()%>/commodities/addInventCommid.do?id=${inventory.id}">
    	进货
    	</c:url>
    </td>
</tr>
</c:forEach>
</tbody>
</table>
</form>
<paging:paging size="${rf.currentPage}" total="${rf.pageCount}" 
   curr="${rf.pageSize}" href="/inventory/list.do"/>

<jsp:include page="../common/footer.jsp"/>

