<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!--  
	<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
-->
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="../common/header.jsp">
	<jsp:param value="UserManager" name="pageTitle"/>
</jsp:include>

<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.0.js">
 </script>
<script type="text/javascript">


function showGroupName(id){
	if($(this).is(":checked")){
		$("#select"+id).attr('disabled',true);
		
	}else{
		$("#select"+id).attr('disabled',false);
		
	}
}

function updateBatch(){
	$("input:checked").each(function(index){
		var usernameid = "username" + index;
		var groupNameid = "select"+index;
		
		var username = $("#"+usernameid).text().trim();
		var groupName = $("#"+groupNameid).find(':selected').val().trim();
		
		$.ajax({
			type : "POST",
			url : "<%=request.getContextPath()%>/userManager/update.do?username=" + username
			+ "&groupName=" +groupName
		});
		
		window.location.reload();
	});
}

function pullBackBatch(){
	$("input:checked").each(function(index){
		var username = $("#username"+index).text().trim();
		if(username == '')return;
		
		var r = confirm('你确定将这些用户拉黑吗？');
		if(r == true){
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()%>/userManager/pullBack.do?username=" + username
			});
			
			window.location.reload();
		}
	});
}

</script>
<h1>User Manager</h1>
<p>
	用户权限信息
</p>
<form action="" method="post">
	<div>
		<input type="button"  value="更新" onclick="updateBatch();"/>
		<input type="button"  value="拉黑" onclick="pullBackBatch();"/>
	</div>
		<input type="hidden" value="fuck" id="test"/>
	<hr/>
	<table border ="1">
		<tr>
			<td>选择</td>
			<td>用户名</td>
			<td>密码</td>
			<td>角色</td>
			<td></td>
		</tr>
	<c:forEach items="${userManagers}" var="userManager" varStatus="item">
		<tr id="tr${item.index}">
			<td><input type="checkbox" id="${item.index}" onchange="showGroupName('<c:out value="${item.index}"/>')"/></td>
			<td id="username${item.index}">
				<c:out value="${userManager.username}"/>
			</td>
			<td>${userManager.password}</td>
			<td>
				
					<select  id="select${item.index}" disabled="disabled">
						<c:if test="${userManager.role == 'Users'}">
							<option value="Users" selected="selected">普通用户</option>
							<option value="Administrators">管理员</option>
						</c:if>
						
						<c:if test="${userManager.role == 'Administrators'}">
							<option value="Users">普通用户</option>
							<option value="Administrators" selected="selected">管理员</option>
						</c:if>
					</select>
				
			</td>
			
			<td>
			
			</td>
		</tr>
	</c:forEach>
	</table>
</form>

<jsp:include page="../common/footer.jsp"/>