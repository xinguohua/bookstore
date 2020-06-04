<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<%@include file="/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		$(".delBtn").click(function(){
			//alert("ok");
			//取出要删除的书名并提示用户  this当前点击的a标签
			//找到有书名的td单元格
			var tdEle = $(this).parents("tr").find("td:first");
			//alert(tdEle.text());
			if(!confirm("确认删除【"+tdEle.text()+"】吗？")){
				//点击取消按钮
				return false;
			}
			
		});
		
		
		//点击确定跳转到对应页面
		$("#gotoPageBtn").click(function(){
			//1、获取到要转向到第几页
			var pn = $("#pn_input").val();
			//2、跳转到对应页面.为了浏览器兼容性
			//location.href="manager/BookManagerServlet?method=page&pn="+pn;
			location.href="${pageContext.request.contextPath}/manager/BookManagerServlet?method=page&pn="+pn;
		});
		
	});

</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<%@include file="/include/manager_menu.jsp" %>
			<!-- <div>
				<a href="book_manager.html">图书管理</a>
				<a href="order_manager.html">订单管理</a>
				<a href="../../index.html">返回商城</a>
			</div> -->
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>	
			<c:forEach items="${page.pageData }" var="book">
				<tr>
					<td>${book.title }</td>
					<td>${book.price }</td>
					<td>${book.author }</td>
					<td>${book.sales }</td>
					<td>${book.stock }</td>
					<!--点击修改通过servlet查询要修改图书的详细信息，然后来到 book_edit.jsp页面进行显示 -->
					<td><a href="manager/BookManagerServlet?method=info&id=${book.id }&pn=${page.pageNo}">修改</a></td>
					<td><a class="delBtn" href="manager/BookManagerServlet?method=del&id=${book.id }">删除</a></td>
				</tr>	
			</c:forEach>	
			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp">添加图书</a></td>
			</tr>	
		</table>
		<div id="page_nav">
		<a href="manager/BookManagerServlet?method=page&pn=1">首页</a>
		<c:if test="${page.hasPrev }">
			<a href="manager/BookManagerServlet?method=page&pn=${page.pageNo-1 }">上一页</a>
		</c:if>
		【${page.pageNo }】
		
		<c:if test="${page.hasNext }">
		<a href="manager/BookManagerServlet?method=page&pn=${page.pageNo+1 }">下一页</a>
		</c:if>
		
		<a href="manager/BookManagerServlet?method=page&pn=${page.totalPage }">末页</a>
		共${page.totalPage }页，${page.totalCount }条记录 到第<input value="${page.pageNo }" name="pn" id="pn_input"/>页
		<input type="button" value="确定" id="gotoPageBtn">
		</div>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>