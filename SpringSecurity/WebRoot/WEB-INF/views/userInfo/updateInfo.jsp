<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
 
 <script type="text/javascript" src="/js/jquery.js"></script>
 <script type="text/javascript">
	$(document).ready(function(){
		
	});
	
	function checkForm(){
		var birthday = $('#birthday').val();
	
	}
</script>
<jsp:include page="../common/header.jsp">
	<jsp:param value="User Index" name="pageTitle"/>
</jsp:include>
<h1>个人中心</h1>
<p>请在这里完善个人信息</p>

<form action="<%=request.getContextPath()%>/userInfo/update.do" method="post" id="userInfo">
	<c:if test="${!empty userInfo.id}">
	<input type="hidden" name="id" value="${userInfo.id}"/>
	</c:if>
	<input type="hidden" name="username" value="<%=request.getUserPrincipal().getName()%>"/>
	<br/>
	<label for="sex">性别</label>:
	<select name="sex">
		<option value="1">男</option>
		<option value="2">女</option>
	</select>
	<br/>
	<label for="phone">联系电话</label>:
	<input id="phone" name="phone" value="${userInfo.phone}" size="20" maxlength="50" type="text"/>
	<br/>
	<label for="birthday">生日</label>
	<input type="date" id="birthday" name="birthday" value="${userInfo.birthday}"/>
	<font color="gray">*如：1999-01-22</font>
	<br/>
	<label for="address">地址</label>:
	<input id="address" name="address" value="${userInfo.address}" size="20" maxlength="50" type="text"/>
	<br />
	<label for="zipcode">邮政编码</label>:
	<input id="zipcode" name="zipcode" value="${userInfo.zipcode}" size="20" maxlength="50" type="text"/>
	<br/>
	<input type="submit" value="保存修改"/>
</form>
  <jsp:include page="../common/footer.jsp"/>