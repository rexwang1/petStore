<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

<form action="<%=request.getContextPath()%>/account/changePassword.do" method="post" id="userInfo">

	<label for="password">密码</label>:
	<input id="password" name="password"  size="20" maxlength="50" type="text"/>
	<br/>
	<input type="submit" value="保存修改"/>
</form>
  <jsp:include page="../common/footer.jsp"/>