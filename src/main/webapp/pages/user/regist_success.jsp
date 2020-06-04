<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%@include file="/include/base.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="static/img/logo.gif" >
				<span class="wel_word"></span>
				<%@include file="/include/login_menu.jsp" %>
			<!-- 	<div>
					<span>欢迎<span class="um_span">孙悟空</span>光临尚硅谷书城</span>
					<a href="../order/order.html">我的订单</a>
					<a href="../../index.html">注销</a>&nbsp;&nbsp;
					<a href="../../index.html">返回</a>
				</div> -->
		</div>
		
		<div id="main">
		
			<h1>注册成功! <a href="index.jsp">转到主页</a></h1>
	
		</div>
		
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>