<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/include/base.jsp"%>
<title>书城首页</title>
<script type="text/javascript">
	$(function(){
		$("#gotoPageBtn").click(function(){
			var pn = $("#pn_input").val();
			location.href="${pageContext.request.contextPath}/client/BookClientServlet?method=pageByPrice&pn="+pn+"&min=${param.min }&max=${param.max}";
		});
	});

</script>
</head>
<body>

	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">网上书城</span>
		<%@include file="/include/login_menu.jsp"%>

	</div>


	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/BookClientServlet">
					<input name="method" value="pageByPrice" type="hidden"/>
					价格：<input type="text" name="min" value="${param.min }"> 元 - 
					<input type="text" name="max" value="${param.max }"> 元
					<button>查询</button>
				</form>
			</div>
			<div style="text-align: center">
				<span>您的购物车中有
					<c:out value="${cart.totalCount }" default="0"></c:out>
				件商品</span>
				<div>
					<c:if test="${!empty lastBook }">
						您刚刚将<span style="color: red">
							${lastBook.title }
						</span>加入到了购物车中
					</c:if>
					<c:if test="${empty lastBook }">
						<span>
							&nbsp;&nbsp;
						</span>
					</c:if>
					
				</div>
			</div>
			<c:forEach items="${page.pageData }" var="book">
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${book.imgPath }" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">书名:</span> 
							<span class="sp2">${book.title }</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span> 
							<span class="sp2">${book.author }</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span> 
							<span class="sp2">￥${book.price }</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span> 
							<span class="sp2">${book.sales }</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span> 
							<span class="sp2">${book.stock }</span>
						</div>
						<div class="book_add">
							<a href="CartServlet?method=add2cart&id=${book.id }">加入购物车</a>
						</div>
					</div>
				</div>

			</c:forEach>

		</div>

		<div id="page_nav">
			<a href="client/BookClientServlet?method=pageByPrice&pn=1&min=${param.min }&max=${param.max}">首页</a> 
			<c:if test="${page.hasPrev }">
				<a href="client/BookClientServlet?method=pageByPrice&pn=${page.pageNo -1}&min=${param.min }&max=${param.max}">上一页</a> 
			</c:if>
			
			
			<!--1、总页码小于5。显示全部  -->
			<c:if test="${page.totalPage<=5 }">
				<!--设置页码遍历的开始值  -->
				<c:set var="begin" value="1" scope="page"></c:set>
				<c:set var="end" value="${page.totalPage }" scope="page"></c:set>
			</c:if>
			
			<!--2、总页码大于5.显示前两页和后两页  -->
			<c:if test="${page.totalPage>5 }">
				<!-- 1）、当前页小于3，都是显示1-5 -->
				<c:if test="${page.pageNo<3 }">
						<c:set var="begin" value="1" scope="page"></c:set>
						<c:set var="end" value="5" scope="page"></c:set>			
				</c:if>
				<!-- 2）、当前页码大于等于3，显示前两页和后两页 -->
				<c:if test="${page.pageNo>=3 && page.pageNo<page.totalPage-2 }">
					<c:set var="begin" value="${page.pageNo-2 }"></c:set>
					<c:set var="end" value="${page.pageNo+2 }"></c:set>
				</c:if>
				<!--  8页
					4：2 3 4 5 6
					5:3 4 5 6 7
					
					7:4 5 6 7 8  接近尾页总是显示后五页  总页码-4开始到总页码结束
				
				  -->
				<c:if test="${page.pageNo>= page.totalPage-2}">
					<c:set var="begin" value="${page.totalPage-4 }"></c:set>
					<c:set var="end" value="${page.totalPage }"></c:set>
				</c:if>
			</c:if>
			
			<c:forEach begin="${begin }" end="${end }" var="num">
				<!--遍历的五个数字是否是当前页码，如果是，不显示超链接，否则显示超链接  -->
				<c:if test="${page.pageNo == num }">
					<!--当前页码  -->
					【${num}】
				</c:if>
				<!-- 取出的数字不是当前页码，我们加上超链接 -->
				<c:if test="${page.pageNo != num }">
					<a href="client/BookClientServlet?method=pageByPrice&pn=${num }&min=${param.min }&max=${param.max}">${num }</a>
				</c:if>
			</c:forEach>
				
			<c:if test="${page.hasNext }">
				<a href="client/BookClientServlet?method=pageByPrice&pn=${page.pageNo+1 }&min=${param.min }&max=${param.max}">下一页</a> 
			</c:if>
			
			<a href="client/BookClientServlet?method=pageByPrice&pn=${page.totalPage }&min=${param.min }&max=${param.max}">末页</a> 
			共${page.totalPage }页，${page.totalCount }条记录 到第
			<input value="${page.pageNo }" name="pn" id="pn_input" />页 
			<input type="button" value="确定" id="gotoPageBtn">
		</div>

	</div>

	<div id="bottom">
		<span> 尚硅谷书城.Copyright &copy;2015 </span>
	</div>
</body>
</html>