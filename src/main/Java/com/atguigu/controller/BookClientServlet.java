package com.atguigu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.entity.Book;
import com.atguigu.entity.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;

/**
 * Servlet implementation class BookClientServlet
 */
@WebServlet(urlPatterns = "/client/BookClientServlet")
public class BookClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	BookService bs = new BookServiceImpl();

	/**
	 * 分页查询前端数据
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void page(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1、获取分页数据，当前第几页...
		String pn = request.getParameter("pn");
		String ps = request.getParameter("ps");
		// 2、调用service进行查询
		Page<Book> page = bs.getPage(pn, ps);
		// 3、将查出的数据放在域中
		request.setAttribute("page", page);
		// 4、转发到页面进行显示
		request.getRequestDispatcher("/pages/book/list.jsp").forward(request,
				response);
	}
	
	protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(request, response);
		//1、获取到价格区间
		String min = request.getParameter("min");
		String max = request.getParameter("max");
		String pn = request.getParameter("pn");
		String ps = request.getParameter("ps");
		//2、查询价格区间对应的图书
		Page<Book> page = bs.getByPrice(pn, ps, min, max);
		//3、放在域中在页面显示
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/book/list.jsp").forward(request, response);
	}

}
