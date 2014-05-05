<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
 <%@ taglib uri="my-taglib" prefix="paging" %>
 <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.0.js">
 </script>
 <script type="text/javascript">
	 
 function addInShopList(id){
    	var num = $('#'+index+'Num').val();
    	if(checkNum(id,num)){
    		$.ajax({
			type:'POST',
			url:'<%=request.getContextPath()%>/shopList/saveCom.do/?comid='+id+'&num='+num,
			dataType: 'json',
			success : function(data){
				alert('加入成功');
			}
			});
    	};
    }
	function reduce(index){
		var num = $('#'+index+'Num').val();
		if(Number(num) >0 ){
			$('#'+index+'Num').val().trim = --num;
		};
	}
	
	function increase(id,index){
		var num = $('#'+index+'Num').val();
	
		if(checkNum(id,Number(num)-1)){
			$('#'+index+'Num').val().trim = ++num;
		};
		
	}
	
	//检查对应的库存的商品数量
	function checkNum(id,num){
	
		$.ajax({
			type:'POST',
			url:'<%=request.getContextPath()%>/commoditiez/checkInvenNum.do?id='+id+'&num='+num,
			dataType: 'json',
			success : function(data){
				if(data.valid == 'false'){
					alert('超出库存的商品数量范围！');
					return false;
				}
			}
		});
		return true;
	}
</script>
<jsp:include page="../common/header.jsp">
	<jsp:param value="Home" name="pageTitle"/>
</jsp:include>
<h1>欢迎来到宠物用品店</h1>
<p>
	我们为你精心准备很多用品给你的心爱的宠物
</p>

<p>
	
</p>
<form action="<%=request.getContextPath()%>/commoditiez/list.do" method="post">

	<div>

		<table>
			<tr>
				<td>
					<label for="brand">品牌</label>：<input type="text" name="brand" />
				</td>
				<td>
					<label for="brand">商品名</label>：<input type="text" name="name" />
				</td>
				<td>
					<label for="type">类型</label>：
				<select name="type">
					<option value="-1" >---请选择----</option>
					<option value="dog">狗狗用品</option>
					<option value="cat">猫猫用品</option>
					<option value="acquriumn">水族用品</option>
				</select>
				</td>
				
				<td></td>
			</tr>
			<tr>
				<td>
				
				<input type="checkbox" id="price" name="canOrder" value="true"/>
				<label for="brand">按价格</label>：
				<input type="radio" name="priceOrder" value="desc"/>从高到低
				<input type="radio" name="priceOrder" value="asc" checked="checked"/>从低到高
				
				</td>
				<td>
					<label for="brand">价格范围</label>：<br/>
					<input style="height: 26px; width: 77px; " name="lowPrice"/>  到
					 <input style="width: 77px; " name="highPrice"/>元
				</td>
				<td><input type="submit" value="查询"/></td>
			</tr>
		</table>

		
	</div>
	<table border="1">  
	<thead>
	<tr>
	<td>类型</td>
	<td>品牌</td>
	<td>商品名</td>
    <td>编号</td>  
    <td>单价(元)</td>  
    <td>规格</td>  
    <td>生产日期</td>
    <td>过期日期</td>
    <td></td>
    <td></td>   
	</tr>
	</thead>
<tbody>

<c:forEach items="${rf.items}" var="commodities" varStatus="status">
<tr>  
	<td>
		<c:choose>
			<c:when test="${commodities.inventory.type == 'dog'}">狗狗用品</c:when>
			<c:when test="${commodities.inventory.type == 'cat'}">猫猫用品</c:when>
			<c:otherwise>水族用品</c:otherwise>
		</c:choose>
	</td>
	<td><c:out value="${commodities.inventory.brand}"/></td>
	<td><c:out value="${commodities.inventory.name}"/></td>
    <td><c:out value="${commodities.itemNo}"/></td>  
    <td><c:out value="${commodities.price}"/></td>
    <td><c:out value="${commodities.standard}"/></td>  
    <td>
    <fmt:formatDate value="${commodities.createDate}" pattern="yyyy-MM-dd" type="both"/>
    </td>
    <td>
    <fmt:formatDate value="${commodities.expiryDate}" pattern="yyyy-MM-dd" type="both"/>
    </td>
    <td><a href="<%=request.getContextPath()%>/commoditiez/detail.do?id=${commodities.itemNo}">详情</a></td>
    <td>
    	
    </td>
</tr>  
</c:forEach>  
</tbody>
</table>
<c:if test="${!empty rf.items}">  
<paging:paging size="${rf.currentPage}" total="${rf.pageCount}" 
   curr="${rf.pageSize}" href="/commodities/list.do"/>
</c:if>

</form>


 
<jsp:include page="../common/footer.jsp"/>

