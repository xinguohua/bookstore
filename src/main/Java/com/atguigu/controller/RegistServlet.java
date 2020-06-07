package com.atguigu.controller;

import java.io.IOException;

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
 * Servlet implementation class RegistServlet
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserService us = WebUtils.getBean(UserService.class);;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistServlet() {
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
		// 用户注册
		// 1、获取用户输入数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		// 2、封装对象
		User user = new User(null, username, password, email);
		// 3、调用service中的regist进行注册
		boolean regist = us.regist(user);
		// 4、判断方法的返回值。
		if (regist) {
			// 如果是true：注册成功：重定向来到注册成功页面 pages/user/regist_success.html
			response.sendRedirect(request.getContextPath()
					+ "/pages/user/regist_success.jsp");
		} else {
			// 如果是false：注册失败：转发来到注册页面
			//在注册页面能提示错误消息
			//将错误消息放在域对象中，去页面取出即可
			request.setAttribute("msg", "该用户名已存在！");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(
					request, response);
		}

	}

}
