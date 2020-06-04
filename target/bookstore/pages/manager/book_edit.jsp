<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
<%@include file="/include/base.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">编辑图书</span>
			<%@include file="/include/manager_menu.jsp" %>
			<!-- <div>
				<a href="book_manager.html">图书管理</a>
				<a href="order_manager.html">订单管理</a>
				<a href="../../index.html">返回商城</a>
			</div> -->
		</div>

		<div id="main">
			<!-- 表单method默认是get。表单项中的数据会把url地址后的数据全部覆盖 -->
			<form action="manager/BookManagerServlet">
				<input name="method" value="update" type="hidden"/>
				<!--通过id来识别是添加操作还是修改操作  -->
				<input  name="id" value="${book.id }" type="hidden">
				<!-- 保存来源的页码 -->
				<input name="pn" value="${param.pn }" type="hidden"/>
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
					
						<td><input name="title" type="text" value="${book.title }"/></td>
						<td><input name="price" type="text" value="${book.price }"/></td>
						<td><input name="author" type="text" value="${book.author }"/></td>
						<td><input name="sales" type="text" value="${book.sales }"/></td>
						<td><input name="stock" type="text" value="${book.stock }"/></td>
						
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>
		
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>