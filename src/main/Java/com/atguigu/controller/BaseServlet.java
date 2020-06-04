package com.atguigu.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//解决post请求乱码
		request.setCharacterEncoding("utf-8");
		// 登陆注册都请求UserServlet
		// method代表告诉服务器我是那个请求
		String method = request.getParameter("method");
		// 1、先判断是登陆请求还是注册请求还是其他...请求
		//
		System.out.println("请求的方法：" + method);
		// getDeclaredMethod(方法名，参数列表)
		try {
			Method m = this.getClass().getDeclaredMethod(method,
					HttpServletRequest.class, HttpServletResponse.class);
			//invoke(传入要调用方法的对象，传入参数)
			m.setAccessible(true);
			m.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
			//将捕获到的异常抛出去
			throw new RuntimeException();
		}

	}

}
