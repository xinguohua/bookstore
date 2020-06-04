package com.atguigu.test;

import java.util.List;

import com.atguigu.entity.Page;
import org.junit.Test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.entity.Book;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;

public class BookDaoTest {
	
	@Test
	public void test1(){
		BookDao bd = new BookDaoImpl();
		Book book = new Book(null, "文文", "吴承恩", 100.98, 1000, 10000, "static/img/xiyouji.jpg");
		int i = bd.addBook(book);
		System.out.println("保存完成："+i);
	}
	
	
	@Test
	public void test2(){
		BookDao bd = new BookDaoImpl();
		Book book = new Book(1, "东游记", "吴承恩", 100.98, 1000, 10000, "static/img/xiyouji.jpg");
		int i = bd.updateBook(book);
		System.out.println("修改完成："+i);
	}
	
	@Test
	public void test3(){
		BookDao bd = new BookDaoImpl();
		Book book = new Book();
		book.setId(1);
		Book one = bd.getOne(book);
		System.out.println("查出的图书："+one);
	}
	
	@Test
	public void test4(){
		BookDao bd = new BookDaoImpl();
		List<Book> all = bd.getAll();
		System.out.println("所有图书："+all);
	}
	
	/**
	 * 测试service的分页方法
	 */
	@Test  
	public void test5(){
		BookService bs = new BookServiceImpl();
		Page<Book> page = bs.getPage("2", "4");
		System.out.println("当前页码："+page.getPageNo());
		System.out.println("是否有上一页："+page.isHasPrev());
		System.out.println("是否有下一页："+page.isHasNext());
		System.out.println("总页码："+page.getTotalPage());
		System.out.println("总记录数："+page.getTotalCount());
		System.out.println("查出的数据："+page.getPageData());
		//System.out.println(page);
	}
	
	@Test  
	public void test6(){
		BookService bs = new BookServiceImpl();
		Page<Book> page = bs.getByPrice("1", "4", "11.56", "30");
		System.out.println("当前页码："+page.getPageNo());
		System.out.println("是否有上一页："+page.isHasPrev());
		System.out.println("是否有下一页："+page.isHasNext());
		System.out.println("总页码："+page.getTotalPage());
		System.out.println("总记录数："+page.getTotalCount());
		System.out.println("查出的数据："+page.getPageData());
		//System.out.println(page);
	}
	

}
