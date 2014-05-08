<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
 
<jsp:include page="../common/header.jsp">
	<jsp:param value="ShopList Index" name="pageTitle"/>
</jsp:include>

<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.0.js">
 </script>
<script type="text/javascript">


var errorMessage = '';
$(document).ready(function(){
  $("#showOrHide").click(function(){
  	$("#commodList").toggle();
  });
  
  $("#submitShopList").click(function(){
  	 alert("Fuck!");
  	 submitShopList();
  });
  
});
 
 
 function selectPayType(){
 	var selectedValue = $('#selectType').find('option:selected').val();
 	if(selectedValue == "1"){
 		$('#cardNum').show();
 		$('#canSubmit').val('true');
 	}
 	else{
 		$('#cardNum').hide();
 		$('#canSubmit').val('false');
 	}
 }
 
 
 
 function cancelShopList(){
 	var id = $('#shopListID').val();
 	var r = confirm('你确定撤销吗？撤销后，该清单不被保存');
 	if(r == true){
 		$.ajax({
			type:'POST',
			url:'<%=request.getContextPath()%>/shopList/deleteShopList.do?id='+id,
			
			success : function(data){
				alert('撤销成功');
				top.location="<%=request.getContextPath()%>/home.do";
			}
		});
 	}
 }
 
 function submitShopList(){
 	
 	if(!checkSubmit()){
 		return;
 	}
 	var cardNo = $('#cardNo').val();
 	var selectedValue = $('#selectType').find('option:selected').val();
 	if(selectedValue == "0"){
 		cardNo = '0';
 	}
 	var submitForm = $('#saveShopList');
 	
 	var url = '<%=request.getContextPath()%>/shopList/updateShopList.do?cardNo='+cardNo;
 	submitForm.attr('action',url);
 	submitForm.submit();
 }
 
 function checkSubmit(){
 	
 	//重新置为‘’
 	if(hasVal($('#addressee').val()) && hasVal($('#consigneeAddress').val())
 		&& hasVal($('#consigneePhone').val()) && validCard($('#cardNo').val())){
 	
 		return true;
 	}
 	alert(errorMessage);
 	errorMessage='';
 	return false;
 }		
 
 function hasVal(value){
 	var isTrue = value !=null && value.trim()!='';
 	if(!isTrue){
 		alert("不能为空！");
 	}
 	return isTrue;
 }
 
 //判断银行的可用性
 function validCard(value){
 	var selectedValue = $('#selectType').find('option:selected').val();
 	if(selectedValue !='cashOnline'){
 		return true;
 	}
 	
 	//卡号是六位以及以上的数字
 	reg = /^(\d{6,})$/;
 	if(!reg.test(value)){
 		errorMessage += '请输入正确的银行卡号<br/>';
 		return false;
 	}
 	
 	var checkedValue = $('input[name="name"]:checked').val();
 	var cardNo = $('#cardNo').val();
 	var money = $('#money').val();
 
 	//传给后台处理的url
 	var postUrl = '<%=request.getContextPath()%>/bankCard/checkCard.do?cardNo='+cardNo
 		+'&name='+checkedValue + '&money=' + money;
 	
 	$.ajax({
			type:'POST',
			url:postUrl,
			
			success : function(data){
				if(data.valid=='NO_EXISTED'){
					errorMessage += '该银行卡卡号不存在<br/>';
					return false;
				}
				
				if(data.valid=='NO_ENOUGH'){
					errorMessage += '对不起，该银行卡余额不足<br/>';
					return false;
				}
			}
		});
		
		//alert("card:"+$("input[name='cardNo']").val());
		return true;
 }
 
 function isLinkCall(value){
 	
 	regPhone = /^(\d{2,3}-)?\d{11}$/;
 	regMobile=/^(\d{3,4}-)?[1-9]\d{6,7}$/; 
 	
 	if(!regPhone.test(value) || !regMobile.test(value)){
 		errorMessage += '请输入正确的联系方式<br/>';
 		return false;
 	}
 	
 	return true;
 }
 
 function delCommodInShop(id){
 	if(confirm("你确认退订吗？")){
 		var shopListID = $('#shopListID').val();
 		$.ajax({
				type:"POST",
				url:"<%=request.getContextPath()%>/shopList/deleteCom.do?id="+ shopListID + "&comid=" + id,
						dataType : "json",
						success : function(data) {
							if (data.del == "true") {
								$("#" + id).remove();
								window.location.reload();
							} else {
								alert("退订失败！");
							}
						},
						error : function() {
							alert("网络连接出错！");
						}
					});
 	}
 }
</script>
<h1>货物清单</h1>
<p>注意：*为必选必填选</p>
<form action="" method="post" id="saveShopList">
<table>  
	<thead>
		<tr>
			<th style="font-size:24px;">个人订单</th>
		</tr>
	</thead>
	<tbody>
	<tr>  
    <td>清单号</td>  
    <td>原购买总额</td>  
    <td>购买数量</td>
    <td>订单日期</td>  
    <td>状态</td>
    <td></td>
	</tr>  

<tr>  
    <td><input type="text" disabled="disabled" name="id" value="${shopList.id}" id="shopListID"/></td>  
    <td><input type="text" disabled="disabled" name="money" value="${shopList.money}" id="money"/></td>  
    <td><input type="text" disabled="disabled" name="amount" value="${shopList.amount}"/></td>   
    <td>
    <input type="text" disabled="disabled" name="createDate" value="${shopList.createDate}"/>
    </td>
    <td>
    <input type="text" disabled="disabled" name="status" value="${shopList.status}"/>
    </td>
    <td><input type="button" id="cancel" value="撤销" onclick="cancelShopList();"></td>
</tr>  
</tbody>
</table>
<input type="button" value="待购商品列表" id="showOrHide" title="显示或隐藏列表">
<br/>
<hr/>
<div id="commodList">
	<table>
		<thead>
		<tr>
			<th style="font-size:24px;">订单商品</th>
		</tr>
	</thead>
	<tbody>
	<tr>  
    <td>产品编号</td>  
    <td>产品名称</td>  
    <td>单价</td>
     
    <td>类型</td>
    <td></td>
	</tr>  
<c:forEach items="${shopList.commoditiezs}" var="commod">
<tr id="<c:out value="${commod.itemNo}"/>">  
    <td><c:out value="${commod.itemNo}"/></td>  
    <td><c:out value="${commod.inventory.name}"/></td>  
    <td><c:out value="${commod.price}"/></td>  
    
    <td>
    	<c:choose>
			<c:when test="${commodities.inventory.type == 'dog'}">狗狗用品</c:when>
			<c:when test="${commodities.inventory.type == 'cat'}">猫猫用品</c:when>
			<c:otherwise>水族用品</c:otherwise>
	   </c:choose>
    </td>
    <td><input type="button" id="cancel" value="退订" onclick="delCommodInShop('<c:out value="${commod.itemNo}"/>');"></td>
</tr>  
</c:forEach>
</tbody>
	</table>
</div>
<br/> 
 <hr/> 
  <div id="consigneeInfo">
  	<label>收件人</label>：<input name="addressee" type="text" value="${shopList.addressee}" id="addressee"/><font color="gray">*</font><br/>
  	<label>收件地址</label>：<input name="consigneeAddress" type="text" value="${shopList.consigneeAddress}" id="consigneeAddress"/><font color="gray">*</font><br/>
  	<label>收件人电话号码(手机号)</label>：<input name="consigneePhone" type="text" value="${shopList.consigneePhone}" id="consigneePhone"/><font color="gray">*</font><br/>
  </div>
  
  <hr/>
  <div id="payType" onchange="selectPayType();">
  	<c:if test="${!empty shopList.paymentType}">
  		<input type="hidden" name="shopListID" value="${shopList.paymentType.shopListID}"/>
  	</c:if>
  	<label>支付方式</label>：
  	<select name = "typeID" id="selectType">
  		<option value="0">货到付款</option>
  		<option value="1">网银</option>
  	</select>
  	
  	<br/><br/>
  	
  	<div id="cardNum" style="display:none;">
  	<INPUT type="hidden" id="canSubmit" name="canSubmit" value="false"/>
  	<label for="name"><b>银行卡类型</b><font color="gray">*</font></label>：
  	<p>
  	<span><input type="radio" name="name" value="CBC"/>中国建设银行</span>
  	<span><input type="radio" name="name" value="BC"/>中国银行</span>
  	<span><input type="radio" name="name" value="ABC"/>中国农业银行</span>
  	<span><input type="radio" name="name" value="ICBC"/>中国工商银行</span>
  	<span><input type="radio" name="name" value="CMB"/>中国招商银行</span>
  	</p>
  	<p>
  	<span><input type="radio" name="name" value="PSBC"/>中国邮政存储银行</span>
  	<span><input type="radio" name="name" value="CTB"/>中国交通银行</span>
  	<span><input type="radio" name="name" value="CUB"/>中国农村信用社</span>
  	<span><input type="radio" name="name" value="HXB"/>华夏银行</span>
  	</p>
  	<br/>
  	<label for="cardNo"><b>银行卡卡号</b><font color="gray">*</font></label>：
  	<input type="text" name="cardNo" id="cardNo"/>
  	
  	<br/>
  	</div>
  </div>
  
  <br/>
  <hr/>
  <div style="text-align:center">
  <input type="button" id="submitShopList" value="确定订购"/>
  </div>
</form>

<jsp:include page="../common/footer.jsp"/>
   
