package com.atguigu.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.entity.User;
import com.atguigu.service.BookService;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.code.kaptcha.Constants;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = WebUtils.getBean(UserService.class);;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void login(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		// 进行登陆
		/*
		 * // 1、获取用户名密码 String username = request.getParameter("username");
		 * String password = request.getParameter("password"); // 2、将数据封装为对象
		 * User user = new User(null, username, password, null);
		 */

		// 自动化封装数据
		User user = WebUtils.param2Bean2(request, new User());
		System.out.println("封装的user：" + user);
		// 3、调用service登陆方法
		User login = us.login(user);
		// 4、判断返回值
		// 如果返回Null；登陆失败，转发来到登陆页面 pages/user/login.html
		if (login == null) {

			request.setAttribute("msg", "用户名密码错误!");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(
					request, response);

		} else {
			// 如果返回User对象：登陆成功。重定向到登陆成功页面 /pages/user/login_success.html
			// 将登陆的用户保存在session中
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", login);
			response.sendRedirect(request.getContextPath()
					+ "/pages/user/login_success.jsp");
		}
	}

	protected void regist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// 用户注册
		/*
		 * // 1、获取用户输入数据 String username = request.getParameter("username");
		 * String password = request.getParameter("password"); String email =
		 * request.getParameter("email");
		 * 
		 * // 2、封装对象 User user = new User(null, username, password, email);
		 */
		// 判断验证码是否正确。
		// 1、取出验证码的实际内容。服务端的验证码。将验证码存在session中
		// 保存在KAPTCHA_SESSION_KEY字段中
		HttpSession session = request.getSession();
		String code = (String) session
				.getAttribute(Constants.KAPTCHA_SESSION_KEY);

		// 获取浏览器带过来的验证码
		String code2 = request.getParameter("code");
		if (!code2.equals(code)) {
			// 验证失败
			request.setAttribute("msg", "验证码错误！");
			// 转发到regist页面
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(
					request, response);
			return;
		}

		User user = WebUtils.param2Bean2(request, new User());
		System.out.println("封装的user：" + user);
		// 3、调用service中的regist进行注册
		boolean regist = us.regist(user);
		// 4、判断方法的返回值。
		if (regist) {
			// 如果是true：注册成功：重定向来到注册成功页面 pages/user/regist_success.html
			response.sendRedirect(request.getContextPath()
					+ "/pages/user/regist_success.jsp");
		} else {
			// 如果是false：注册失败：转发来到注册页面
			// 在注册页面能提示错误消息
			// 将错误消息放在域对象中，去页面取出即可

			request.setAttribute("msg", "该用户名已存在！");
			// response.setContentType("text/html;charset=utf-8");
			// response.getWriter().write("<h1>这是动态写的</h1>");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(
					request, response);

		}
	}

	/*
	 * protected void updatePwd(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // TODO Auto-generated
	 * method stub super.doPost(request, response); }
	 */

	/**
	 * 用户注销方法
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(request, response);
		HttpSession session = request.getSession();
		// 清除session
		session.invalidate();
		// 回到首页
		//request.getRequestDispatcher("/index.jsp").forward(request, response);
		
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	
	/**
	 * 用户是否存在
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void isExist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(request, response);
		//1、获取请求参数封装对象
		User user = WebUtils.param2Bean2(request, new User());
		//2、判断用户是否存在
		boolean b = us.isExist(user);
		//3、
		if(b){
			//用户已经存在
			response.getWriter().write("1");
		}else{
			response.getWriter().write("0");
		}
	}
}
