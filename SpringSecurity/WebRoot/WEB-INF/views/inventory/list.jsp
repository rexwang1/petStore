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
<a href="<%=request.getContextPath()%>/inventory/addInventory.do">加入新的库存商品</a><br/>
<form action="<%=request.getContextPath()%>/inventory/list.do" method="post">
	<table border="1">  
	<thead style="color:red;">
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
    <td>
    	<c:if test="${inventory.type == 'dog'}">狗狗用品</c:if>
    	<c:if test="${inventory.type == 'cat'}">猫猫用品</c:if>
    	<c:if test="${inventory.type == 'aquarium'}">水族用品</c:if>
    </td>
    <td><c:out value="${inventory.suit}"/></td>
    <td><c:out value="${inventory.material}"/></td>
    <!-- 详情主要显示原料，连接商品库表显示库存和进货日期-->
    <td>
    	<c:choose>
    	<c:when  test="${!empty inventory.commoditiezs}">
    		<a href="<%=request.getContextPath()%>/commoditiez/detail.do?id=${inventory.id}">
    		详情
    		</a>
    	</c:when>
    	<c:otherwise>
    		<font color="gray">库存为空</font>
    	</c:otherwise>
    	</c:choose>
    </td>  
    <td>
    	<a href="<%=request.getContextPath()%>/commoditiez/addCommod.do?id=${inventory.id}">
    		进货
    	</a>
    	
    </td>
</tr>
</c:forEach>
</tbody>
</table>

<c:if test="${!empty rf.items}">
<paging:paging size="${rf.currentPage}" total="${rf.pageCount}" 
   curr="${rf.pageSize}" href="/inventory/list.do"/>
</c:if>
<jsp:include page="../common/footer.jsp"/>
</form>
<br/>