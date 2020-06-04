<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
<%@include file="/include/base.jsp"%>
<style type="text/css">
h1 {
	text-align: center;
	margin-top: 200px;
}
</style>
</head>
<body>

	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">我的订单</span>
		<%@include file="/include/login_menu.jsp"%>
		<!-- <div>
				<span>欢迎<span class="um_span">孙悟空</span>光临尚硅谷书城</span>
				<a href="../order/order.html">我的订单</a>
				<a href="../../index.html">注销</a>&nbsp;&nbsp;
				<a href="../../index.html">返回</a>
			</div> -->
	</div>

	<div id="main">
		<c:if test="${empty orders }">
			<h1>
				当前没有任何订单，<a href="index.jsp">去购买吧</a>
			</h1>
		</c:if>

		<c:if test="${!empty orders }">

			<table>

				<tr>
					<td>订单号</td>
					<td>日期</td>
					<td>金额</td>
					<td>状态</td>
					<td>详情</td>
				</tr>
				<c:forEach items="${orders }" var="order">
					<tr>
						<td>${order.id }</td>
						<td>${order.createDate }</td>
						<td>${order.totalMoney }</td>
						<td><c:if test="${order.status==0 }">
							未发货
						</c:if> <c:if test="${order.status==1 }">
							已发货<br/>
							<a href="client/OrderClientServlet?method=recevied&id=${order.id }">确认收货</a>
							</c:if> <c:if test="${order.status==2 }">
							交易完成
						</c:if></td>
						<td><a href="#">查看详情</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

	</div>

	<div id="bottom">
		<span> 尚硅谷书城.Copyright &copy;2015 </span>
	</div>
</body>
</html>