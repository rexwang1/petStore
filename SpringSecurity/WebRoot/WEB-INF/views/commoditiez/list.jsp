<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
 <%@ taglib uri="my-taglib" prefix="paging" %>
 <script type="javascript" src="<%=request.getContextPath()%>/js/jquery.js">
 </script>
 
<jsp:include page="../common/header.jsp">
	<jsp:param value="Home" name="pageTitle"/>
</jsp:include>
<h1>欢迎来到宠物用品店</h1>
<p>
	我们为你精心准备很多用品给你的心爱的宠物
</p>
<form action="/commodities/list.do" method="post">
	<table>  
	<thead>
	<tr>
	  
    <td>编号</td>  
    <td>价格</td>  
    <td>规格</td>  
    <td>生产日期</td>
    <td>过期日期</td>
    <td></td>
    <td></td>   
	</tr>
	</thead>
<tbody>
<c:forEach items="${rf.items}" var="commodities">
 <input type="hidden" id="itemNo" value="$(commodities.itemNo)"/>
<tr>  
    <td><c:out value="${commodities.itemNo}"/></td>  
    <td><c:out value="${commodties.price}"/></td>
    <td><c:out value="${commodties.standard}"/></td>  
    <td>
    <fmt:formatDate value="${commodties.createDate}" pattern="yyyy-MM-dd" type="both"/>
    </td>
    <td>
    <fmt:formatDate value="${commodties.expiryDate}" pattern="yyyy-MM-dd" type="both"/>
    </td>
    <td><c:url value="<%=request.getContextPath()%>/inventory/detail.do?id=${commodities.inventory.id}">详情</c:url></td>
    <td>
    	<span>
    		<!-- 商品库存数量 没必要加，该变量的值会发生改变
			<input type="hidden" id="invenAmount" value="$(commodities.inventory.amount)"/> 
    		-->
    		<label for="num">数量</label>：
    		<input type="button" name="reduce" value="-"/>
    		<input type="text" name="num" value="0" size="4" disabled="disabled"/>
    		<input type="button" name="decrease" value="+" />
    		<input type="button" name="addInShopList" value="加入心愿单"/>
    	</span>
    </td>
</tr>  
</c:forEach>  
</tbody>
</table>  
<paging:paging size="${rf.currentPage}" total="${rf.pageCount}" 
   curr="${rf.pageSize}" href="/commodities/list.do"/>
</form>

<script type="text/javascript">
	function reduce(){
		var num = $(this).next('.num').val();
		if(Number(num) >0 ){
			$(this).next('.num').val().trim = --num;
		}
	}
	
	function increase(){
		var num = $(this).next('.num').val();
		if(checkNum(Number(num)-1)){
			$(this).next('.num').val().trim = ++num;
		}
		
	}
	
	//检查对应的库存的商品数量
	function checkNum(num){
		var id = $(this).prev('#itemNo').val();
		$.ajax({
			type:'POST',
			url:'<%=request.getContextPath()%>/commodities/checkInvenNum.do?id='+id+'&num='+num,
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
 
<jsp:include page="../common/footer.jsp"/>

