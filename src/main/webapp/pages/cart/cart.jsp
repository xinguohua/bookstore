<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@include file="/include/base.jsp"%>
<script type="text/javascript">
	$(function(){
		$(".delBtn").click(function(){
			//alert("你好");
			var ele = $(this).parents("tr").find("td:first");
			if(!confirm("确认删除【"+ele.text()+"】吗？")){
				return false;
			}
		});
		
		$(".countInput").change(function(){
			//发送ajax请求
			var t = $(this);
			var v = $(this).val();
			var id = $(this).attr("updateid");
			$.getJSON("${pageContext.request.contextPath}/CartServlet",
					{"method":"updateajax",
					"count":v,
					"id":id
					},function(data){
						//alert(data);
						//取出数据显示到正确的地方
						$(".b_count").text(data.totalCount);
						$(".b_price").text(data.totalMoney);
						t.parents("tr").find("td:eq(3)").text(data.totalPrice);
					}
			);
		});
		//会在失去焦点的时候动态判断文本框内容是否改变，如果改了才触发，否则不会触发
		//blur：只要失去焦点就会触发
	/* 	$(".countInput").change(function(){
			//alert("我出发了。。。");
			// location.href = "${pageContext.request.contextPath}/CartServlet?method=update&count="+$(this).val()
			//		+"&id=";
			var id = $(this).attr("updateid");
			location.href = "${pageContext.request.contextPath}/CartServlet?method=update&count="+$(this).val()
			+"&id="+id;
		}); */
		
	});

</script>
</head>
<body>

	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">购物车</span>
		<%@include file="/include/login_menu.jsp"%>
		<!-- <div>
				<span>欢迎<span class="um_span">孙悟空</span>光临尚硅谷书城</span>
				<a href="../order/order.html">我的订单</a>
				<a href="../../index.html">注销</a>&nbsp;&nbsp;
				<a href="../../index.html">返回</a>
			</div> -->
	</div>

	<div id="main">
		<c:if test="${empty cart.allItems }">
			<!-- 没有任何购物数据 -->
			<center>
				<br />
				<br />
				<br />
				<br />
				<br />
				<br />
				<br />
				<br />
				<h1>
					当前没有购物数据，赶紧去<a href="index.jsp">购物吧</a>
				</h1>
			</center>
		</c:if>

		<c:if test="${!empty cart.allItems }">
			<!-- 有购物数据 -->
			<table>
				<tr>
					<td>商品名称</td>
					<td>数量</td>
					<td>单价</td>
					<td>金额</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${cart.allItems }" var="item">
					<tr>
						<td>${item.book.title }</td>
						<td><input updateid="${item.book.id }" class="countInput"
							name="count" value="${item.count }" style="width: 40px;" /></td>
						<td>${item.book.price }</td>
						<td>${item.totalPrice }</td>
						<td><a class="delBtn"
							href="CartServlet?method=delete&id=${item.book.id }">删除</a></td>
					</tr>
				</c:forEach>



			</table>

			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${cart.totalCount }</span>件商品
				</span> <span class="cart_span">总金额<span class="b_price">${cart.totalMoney }</span>元
				</span> <span class="cart_span"><a href="CartServlet?method=clear">清空购物车</a></span>
				<span class="cart_span"><a
					href="client/OrderClientServlet?method=checkout">去结账</a></span>
			</div>
		</c:if>
	</div>

	<div id="bottom">
		<span> 尚硅谷书城.Copyright &copy;2015 </span>
	</div>
</body>
</html>