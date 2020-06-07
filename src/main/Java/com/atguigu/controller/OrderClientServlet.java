package com.atguigu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.entity.Cart;
import com.atguigu.entity.Order;
import com.atguigu.entity.User;
import com.atguigu.service.BookService;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.utils.WebUtils;

/**
 * Servlet implementation class OrderClientServlet
 */
public class OrderClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrderService os = WebUtils.getBean(OrderService.class);;

	/**
	 * 用户结账请求
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void checkout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//可以获取到线程号
		long id = Thread.currentThread().getId();
		System.out.println("servlet中的线程号："+id);
		// 1、获取到已经登陆用户
		HttpSession session = request.getSession();
		User user = WebUtils.getCurrentUser(request);
		// 2、进行判断，如果没有登陆的用户，跳转到登陆页面，提示用户登陆
		if (user == null) {
			// 用户未登陆
			request.setAttribute("msg", "此操作需要登陆，请先登陆！");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(
					request, response);
		} else {
			// 否则执行结账逻辑
			// 3、从session中获取到购物车对象
			Cart cart = (Cart) session.getAttribute("cart");
			// 4、结账返回订单号
			
			String orderid = os.checkout(user, cart);
			// 5、来到pages/cart/checkout.jsp显示订单号
			session.setAttribute("orderid", orderid);
			/*request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(
					request, response);*/
			response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
		}

	}

	/**
	 * 显示当前用户所有的订单
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void showall(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(request, response);
		// 1、获取到当前用户
		User user = WebUtils.getCurrentUser(request);
		// 2、查出对应的订单
		List<Order> myOrders = os.getMyOrders(user);
		// 3、放在域中转发到页面进行显示
		request.setAttribute("orders", myOrders);

		request.getRequestDispatcher("/pages/order/order.jsp").forward(request,
				response);
	}
	
	/**
	 * 确认收货
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void recevied(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(request, response);
		//1、封装order对象
		Order order = WebUtils.param2Bean2(request, new Order());
		//2、确认收货
		os.recevied(order);
		//3、回到列表显示页面
		response.sendRedirect(request.getContextPath()+"/client/OrderClientServlet?method=showall");
	}

}
