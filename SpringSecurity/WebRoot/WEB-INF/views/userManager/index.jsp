<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!--  
	<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
-->

<jsp:include page="<%=request.getContextPath()%>/views/common/header.jsp">
	<jsp:param value="Register" name="pageTitle"/>
</jsp:include>
<h1>User Manager</h1>
<p>
	用户权限信息
</p>
<form action="" method="post">
	<table>
		<tr>
			<td>用户名</td>
			<td>密码</td>
			<td>权限</td>
			<td>是否有效</td>
			<td>用户是否锁定</td>
			<td>用户是否过期</td>
			<td>验证是否过期</td>
		</tr>
	<c:forEach items="users" var="user">
		<tr>
			<td>${user.username}</td>
			<td>${user.password}</td>
			<td>
				<ul>
					<c:forEach items="user.authorities" var="gantedAuthority">
						<li>
							<c:if test="${gantedAuthority.authority == 'ROLE_USER'}">
								普通用户
							</c:if>
							<c:if test="${gantedAuthority.authority == 'ROLE_ADMIN'}">
								管理员
							</c:if>
						</li>
					</c:forEach>
				</ul>
			</td>
			<td>
				${user.enabled}
			</td>
			<td>
				${user.accountNonExpired}
			</td>
			<td>${user.accountNonLocked}</td>
			<td>${user.credentialsNonExpired}</td>
		</tr>
	</c:forEach>
	</table>
</form>

<jsp:include page="<%=request.getContextPath()%>/views/common/footer.jsp"/>