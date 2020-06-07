package com.atguigu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.entity.User;
import com.atguigu.service.BookService;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 用户操作的service
	private UserService us = WebUtils.getBean(UserService.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		// 进行登陆
		// 1、获取用户名密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 2、将数据封装为对象
		User user = new User(null, username, password, null);
		// 3、调用service登陆方法
		User login = us.login(user);
		// 4、判断返回值
		// 如果返回Null；登陆失败，转发来到登陆页面 pages/user/login.html
		if (login == null) {
			
			request.setAttribute("msg", "用户名密码错误!");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(
					request, response);
			/*PrintWriter writer = response.getWriter();
			writer.write("<!DOCTYPE html><html><head><meta charset='UTF-8'>");
			writer.write("<title>尚硅谷会员登录页面</title>");
			writer.write("<base href='http://localhost:8080/BookStore02/'/>");
			writer.write("<link type='text/css' rel='stylesheet' href='static/css/style.css' >");
			writer.write("</head><body><div id='login_header'>");
			writer.write("<img class='logo_img' alt='' src='static/img/logo.gif' >");
			writer.write("</div>");
			writer.write("<div class='login_banner'><div id='l_content'><span class='login_word'>欢迎登录</span></div>");
			writer.write("<div id='content'><div class='login_form'><div class='login_box'>");
			writer.write("<div class='tit'><h1>尚硅谷会员</h1><a href='pages/user/regist.html'>立即注册</a></div>");
			writer.write("<div class='msg_cont'><b></b><span class='errorMsg'>用户名密码错误</span></div>");*/

		} else {
			// 如果返回User对象：登陆成功。重定向到登陆成功页面 /pages/user/login_success.html
			response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
		}

	}

}
