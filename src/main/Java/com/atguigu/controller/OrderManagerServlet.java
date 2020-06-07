package com.atguigu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.entity.Order;
import com.atguigu.service.BookService;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.utils.WebUtils;

/**
 * Servlet implementation class OrderManagerServlet
 */
public class OrderManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private OrderService os = WebUtils.getBean(OrderService.class);;

	/**
	 * 处理显示所有订单的请求
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void showall(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1、查出所有订单
		List<Order> list = os.getAllOrders();
		// 2、放到域中，转发来到页面
		request.setAttribute("orders", list);
		// 3、转发到pages/manager/order_manager.jsp
		request.getRequestDispatcher("/pages/manager/order_manager.jsp")
				.forward(request, response);
	}
	
	/**
	 * 处理发货请求
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void send(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(request, response);
		//1、封装要发货的订单
		Order order = WebUtils.param2Bean2(request, new Order());
		//2、发货操作
		os.send(order);
		//3、回到页面
		response.sendRedirect(request.getContextPath()+"/manager/OrderManagerServlet?method=showall");
	}

}
