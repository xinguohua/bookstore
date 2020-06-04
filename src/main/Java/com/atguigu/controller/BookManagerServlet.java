package com.atguigu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.entity.Book;
import com.atguigu.entity.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

/**
 * Servlet implementation class BookManagerServlet
 */
public class BookManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private BookService bs = new BookServiceImpl();

	/*
	 * 显示所有图书
	 */
	protected void showall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1、查询出所有图书
		List<Book> all = bs.getAll();
		// 2、将查出的图书数据放在域中，来到页面显示
		request.setAttribute("books", all);
		// 3、转发到book_manager.jsp页面
		request.getRequestDispatcher("/pages/manager/book_manager.jsp")
				.forward(request, response);

	}

	/**
	 * 添加图书
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1、获取用户填写的信息。封装成book对象
		Book book = WebUtils.param2Bean2(request, new Book());
		// 2、保存图书
		bs.add(book);
		// 3、回到列表显示页面。
		// showall(request, response);
		response.sendRedirect(request.getContextPath() + "/manager/BookManagerServlet?method=showall");
	}

	/**
	 * 根据图书的id删除图书
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(request, response);4
		// 1、获取要删除的图书
		Book book = WebUtils.param2Bean2(request, new Book());
		// 2、删除图书
		bs.delete(book);
		// 3、返回到列表页面
		//哪个页面来的回到哪里去；
		//HTTP中请求头有一个字段Referer。代表当前请求来自于那里
		String refer = request.getHeader("referer");
		System.out.println("请求来源地："+refer);
		/*response.sendRedirect(request.getContextPath()
				+ "/manager/BookManagerServlet?method=showall");*/
		response.sendRedirect(refer);
	}

	/**
	 * 查询图书的信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void info(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(request, response);
		// 1、取出数据封装对象
		Book book = WebUtils.param2Bean2(request, new Book());
		// 2、数据中查出的图书的详细信息对象，把他交给页面显示book_edit.jsp
		Book one = bs.getOne(book);
		// 3、将数据放在域中
		request.setAttribute("book", one);
		// 4、转发到页面
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(
				request, response);
	}

	/**
	 * 修改图书信息，添加图书信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(request, response);
		// 1、获取数据封装对象
		
		String pn = request.getParameter("pn");
		Book book = WebUtils.param2Bean2(request, new Book());
		System.out.println("封装的book对象：" + book);
		// 2、修改图书,根据图书id要修改。可以根据封装的对象的id值，来判断是添加操作还是修改操作
		// 如果id=0，代表就是添加操作，因为页面id是空串，封装对象的时候就会把他转为默认值0
		// 如果id>0，代表就是修改操作，页面id不是空串。能封装出数据
		if (book.getId() == 0) {
			// 2、保存图书
			bs.add(book);
			response.sendRedirect(request.getContextPath()
					+ "/manager/BookManagerServlet?method=page&pn="+Integer.MAX_VALUE);
		} else {
			bs.update(book);
			// 3、回到列表显示页面。
			// showall(request, response);
			response.sendRedirect(request.getContextPath()
					+ "/manager/BookManagerServlet?method=page&pn="+pn);
		}
		
		/*String refer = request.getHeader("referer");
		System.out.println("请求来源地："+refer);*/
		
	}

	/**
	 * 图书分页查询
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void page(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(request, response);
		// 页码带的页码为pn
		// 如果页面携带了对应的参数，但是没有值，获取到的空串
		// 如果页面根本就没携带这个参数，获取到的是null
		// 1、获取到页面携带的页码
		String pn = request.getParameter("pn");
		// 2、获取到页面携带的pageSize
		String ps = request.getParameter("ps");

		// 3、分页查询
		Page<Book> page = bs.getPage(pn, ps);
		// 4、将数据放在域中来到manager页面显示
		request.setAttribute("page", page);
		// 5、转发到列表页面
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);

	}
}
