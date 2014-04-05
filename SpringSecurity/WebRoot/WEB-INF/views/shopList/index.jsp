<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<jsp:include page="../common/header.jsp">
	<jsp:param value="ShopList Index" name="pageTitle"/>
</jsp:include>
<h1>货物清单</h1>

<form action="<%=request.getContextPath()%>/shopList/index.do" method="post">
<table>  
	<thead>
		<tr>
			<td>个人订单</td>
		</tr>
	</thead>
	<tbody>
	<tr>  
    <td>清单号</td>  
    <td>总额</td>  
    <td>购买数量</td>
    <td>收件人</td>  
    <td>订单日期</td>  
    <td>状态</td>
    <td></td>
	</tr>  
<c:forEach items="${rf.items}" var="shopList">  
<tr>  
    <td><c:out value="${shopList.id}"/></td>  
    <td><c:out value="${shopList.money}"/></td>  
    <td><c:out value="${shopList.amount}"/></td>
    <td><c:out value="${shopList.addressee}"/></td>    
    <td>
    <fmt:formatDate value="${student.createDate}" pattern="yyyy-MM-dd" type="both"/>
    </td>
    <td><c:out value="${shopList.status}"/></td>
    <td>
    	<c:url value="<%=request.getContextPath()%>/">详情</c:url>
    </td>  
</tr>  
</c:forEach>  
</tbody>
</table>  

</form>

<jsp:include page="../common/footer.jsp"/>
   