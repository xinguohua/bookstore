<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%@include file="/include/base.jsp" %>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>

<!--1、导入jquery库  -->
<script type="text/javascript">
	$(function(){
		//3、ajax判断用户是否存在
		$("input[name='username']").change(function(){
			//1、发送ajax请求
			var v = $(this).val();
			$.get("UserServlet?method=isExist&username="+v,function(data){
				//2、接受响应数据
				if(data==0){
					//alert("用户名可用");
					$(".errorMsg").html("<b style='color:green'>用户名可用</b>");
				}else{
					//3、处理响应数据
					$(".errorMsg").html("<b style='color:red'>用户名不可用</b>");
					$("input[name='username']").focus();//重新聚焦到输入框
				}
			});
		});
		
		//2、点击切换验证码
		$("#codeImg").click(function(){
			//alert("ok");
			//请求如果两次是同一请求，有些浏览器就直接不发了。可以给后面加一串参数
			this.src="code.jpg?m="+Math.random();
		});
		
		
		
		//1、给注册按钮绑定单击事件
		$("#sub_btn").click(function(){
			//2、取出用户输入的值进行验证。
			//验证条件：
			//1）、用户名必须是合法字符  字母加数字_的组合。要求长度在6位以上16以下
			//2）、密码：密码必须是合法字符  字母加数字_的组合。要求长度在6位以上16以下
			//3）、确认密码：必须和密码相同
			//4）、邮箱：符合邮箱的格式   xxx@xxx.ccc
			//5）、验证码：只要不为空即可
			
			//3、取出待验证的值
			//取出用户名
			var username = $("input[name='username']").val();
			var password = $("input[name='password']").val();
			var repwd = $("input[name='repwd']").val();
			var email = $("input[name='email']").val();
			var code = $("input[name='code']").val();
			
			//4、对这些值进行验证
			//正则表达式：正确规则的表达式      功能：就是验证某个字符串是否符合表达式的规定
			var str = "helloworld";
			//创建一个正则表达式
			//创建一个测试用户名和密码的正则表达式
			var regUnAndPwd = /^[a-z0-9_-]{6,16}$/;
			
			//测试用户名
			if(!regUnAndPwd.test(username)){
				//alert("用户名应该为6-16位字母数字_-");
				$(".errorMsg").text("用户名格式错误！");
				return false;
			}
			
			//测试密码
			if(!regUnAndPwd.test(password)){
				//alert("密码必须是字母数字和_-的组合，6-16位");
				$(".errorMsg").text("密码格式错误！");
				return false;
			}
			
			//测试确认密码
			if(password != repwd){
				//alert("两次密码不一致");
				$(".errorMsg").text("两次密码不一致");
				return false;
			}
			
			//测试邮箱
			var emailRegx = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
			//表达式.test(要测试的字符串)   返回true或者false
			if(!emailRegx.test(email)){
				//alert("邮箱格式错误！");
				$(".errorMsg").text("邮箱格式错误！");
				return false;
			}
			
			if(code==""){
				//alert("验证码必须填写！");
				$(".errorMsg").text("验证码必须填写！");
				return false;
			}
			//alert(regx.test(str))
			
			//return false;
		});
	});
</script>
</head>
<body>
		
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg"><!-- 请输入用户名 -->
									${empty msg?"请输入用户名":msg }
								</span>
							</div>
							<div class="form">
								<form action="UserServlet" method="post">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" 
									tabindex="1" name="username" value="${param.username }"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" 
									autocomplete="off" tabindex="1" name="email" 
									value="${param.email }"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" name="code"/>
									<!-- 点击图片切换验证码 -->
									<img id="codeImg" alt="" src="code.jpg" style="float: right; margin-right: 40px;width: 80px;height: 40px;">									
									<br />
									<br />
										<input type="hidden" name="method" value="regist"/>
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>