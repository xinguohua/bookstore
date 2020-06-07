package com.atguigu.service.impl;

import java.util.List;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.entity.Book;
import com.atguigu.entity.Page;
import com.atguigu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bd;

	@Override
	public boolean add(Book book) {
		// TODO Auto-generated method stub

		return bd.addBook(book) > 0;
	}

	@Override
	public boolean delete(Book book) {
		// TODO Auto-generated method stub
		return bd.deleteBook(book) > 0;
	}

	@Override
	public boolean update(Book book) {
		// TODO Auto-generated method stub
		return bd.updateBook(book) > 0;
	}

	@Override
	public Book getOne(Book book) {
		// TODO Auto-generated method stub
		return bd.getOne(book);
	}

	@Override
	public List<Book> getAll() {
		// TODO Auto-generated method stub
		return bd.getAll();
	}

	@Override
	public Page<Book> getPage(String pageNo, String pageSize) {
		// TODO Auto-generated method stub
		Page<Book> page = new Page<>();
		// 给Page中设置相应的内容
		int pn = 1;
		try {
			pn = Integer.parseInt(pageNo);
		} catch (NumberFormatException e) {
			//e.printStackTrace();
		}

		// 如果页面传递传递的数据会转型异常，我们就设置默认值
		int ps = page.getPageSize();
		try {
			ps = Integer.parseInt(pageSize);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		page.setPageNo(pn);

		// List<Book> all = bd.getAll();

		// 我们应该调用数据库查询语句直接查出记录数即可。
		// select count(*) from bs_book
		int totalCount = bd.getTotalCount();
		page.setTotalCount(totalCount);
		page.setPageSize(ps);
		// 设置的时候可以判断一下，当前页码是否是非法的，<1 >总页码
		// 根据ps和total来计算，
		// int totalPage = page.getTotalPage();

		page.setPageNo(pn);

		// 将你之前需要的内容设置进去，调用对应的方法就会进行计算
		List<Book> list = bd
				.getBooksByPage(page.getIndex(), page.getPageSize());
		page.setPageData(list);

		// bd.getBooksByPage(index, size);
		return page;
	}

	@Override
	public Page<Book> getByPrice(String pageNo, String pageSize, String min,
			String max) {
		// TODO Auto-generated method stub
		// 1、创建page对象
		Page<Book> page = new Page<Book>();
		// 2、设置相应的属性
		// 页码转换失败默认是第一页
		int pn = 1;
		try {
			pn = Integer.parseInt(pageNo);
		} catch (Exception e) {
			//e.printStackTrace();
		}

		// 默认是系统规则
		int ps = page.getPageSize();
		try {
			ps = Integer.parseInt(pageSize);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		double minPrice = 0.0;
		try {
			minPrice = Double.parseDouble(min);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		double maxPrice = Double.MAX_VALUE;
		try {
			maxPrice = Double.parseDouble(max);
		} catch (Exception e) {
			//e.printStackTrace();
		}

		page.setPageSize(ps);
		// 查出当前价格区间的总记录数
		int countByPrice = bd.getCountByPrice(minPrice, maxPrice);
		page.setTotalCount(countByPrice);

		page.setPageNo(pn);
		// 3、调用dao进行查询
		List<Book> list = bd.getPageByPrice(page.getIndex(),
				page.getPageSize(), minPrice, maxPrice);

		// 4、查询结果封装并返回
		page.setPageData(list);
		
		return page;
	}

}
