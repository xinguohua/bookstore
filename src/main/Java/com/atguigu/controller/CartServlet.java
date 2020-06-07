package com.atguigu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.atguigu.entity.Book;
import com.atguigu.entity.Cart;
import com.atguigu.entity.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

/**
 * 
 * 处理和购物车相关的请求 Servlet implementation class CartServlet
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	BookService bs = WebUtils.getBean(BookService.class);

	/**
	 * 处理加入到购物车的请求
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void add2cart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1、获取到购物车。我们购物车都是存在session中。
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		// 判断购物车是否存在
		if (cart == null) {
			// 如果不存在，创建一个购物车放在session中·
			cart = new Cart();
			session.setAttribute("cart", cart);
		}

		// 2、页面传递过来参数
		Book book = WebUtils.param2Bean2(request, new Book());
		// 3、从数据库中拿到图书的详细信息
		Book one = bs.getOne(book);
		// 4、把图书添加到购物车
		cart.addBook2Cart(one);

		// 将刚才添加的图书放在session中来进行共享
		session.setAttribute("lastBook", one);
		// 5、哪儿来的回哪儿去
		String refer = request.getHeader("referer");
		response.sendRedirect(refer);

		System.out.println("购物车总数：" + cart.getTotalCount());
		System.out.println("购物车总金额：" + cart.getTotalMoney());
		System.out.println("购物车中的购物项:" + cart.getAllItems());
	}

	/**
	 * 清空购物车
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void clear(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(request, response);
		// 1、拿到购物车
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");

		// 2、清空购物车
		cart.clear();
		// 3、回到购物车页面
		String refer = request.getHeader("referer");
		response.sendRedirect(refer);
	}

	/**
	 * 删除某个购物项
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(request, response);
		// 1、拿到购物车
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");

		// 2、获取页面传递过来的id
		String id = request.getParameter("id");
		cart.deleteItem(id);

		// 3、回到购物车页面
		String refer = request.getHeader("referer");
		response.sendRedirect(refer);
	}

	protected void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(request, response);
		// 1、拿到购物车
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");

		// bookid和count'都是来源于页面
		String id = request.getParameter("id");
		String count = request.getParameter("count");
		cart.updateCount(id, count);

		// 3、回到购物车页面
		String refer = request.getHeader("referer");
		response.sendRedirect(refer);
	}
	
	protected void updateajax(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1、拿到购物车
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");

		// bookid和count'都是来源于页面
		String id = request.getParameter("id");
		String count = request.getParameter("count");
		cart.updateCount(id, count);
		
		//2、返回修改的部分数据，获取修改后的总件数和总金额
		double money = cart.getTotalMoney();
		int count2 = cart.getTotalCount();
		CartItem item = cart.getItem(id);
		double totalPrice = item.getTotalPrice();//修改后的某一项价格
		
		//3、将要返回的数据封装
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalMoney", money);
		map.put("totalCount", count2);
		map.put("totalPrice", totalPrice);
		String string = JSON.toJSONString(map);
		response.getWriter().write(string);
	}

}
