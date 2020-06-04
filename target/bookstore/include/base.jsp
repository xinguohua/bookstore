<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- base标签的所有的内容都应该是动态获取的，而不是写死的 
http://192.168.10.165:8080/BookStore03/
协议名	主机名   	端口号	项目名

-->
<!--http  192.168.10.165  8080  /BookStore03-->

<base href="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %><%=request.getContextPath() %>/"/>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>