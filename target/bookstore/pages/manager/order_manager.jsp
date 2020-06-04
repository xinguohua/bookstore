<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
<%@include file="/include/base.jsp" %>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
			<%@include file="/include/manager_menu.jsp" %>
			<!-- <div>
				<a href="book_manager.html">图书管理</a>
				<a href="order_manager.html">订单管理</a>
				<a href="../../index.html">返回商城</a>
			</div> -->
	</div>
	
	<div id="main">
	<c:if test="${empty orders }">
		<h1>还没有任何订单</h1>
	</c:if>
	<c:if test="${!empty orders }">
		
	
		<table>
			<tr>
				<td>订单号</td>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
			</tr>	
			
			<c:forEach items="${orders }" var="order">
				<tr>
					<td>${order.id }</td>
					<td>${order.createDate }</td>
					<td>${order.totalMoney }</td>
					<td><a href="#">查看详情</a></td>
					<td>
						<c:if test="${order.status==0 }">
							<a href="manager/OrderManagerServlet?method=send&id=${order.id }">
							点击发货</a>
						</c:if>
						<c:if test="${order.status==1 }">
							已发货
						</c:if>
						<c:if test="${order.status==2 }">
							交易完成
						</c:if>
					</td>
				</tr>	
			</c:forEach>	
		</table>
		</c:if>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>