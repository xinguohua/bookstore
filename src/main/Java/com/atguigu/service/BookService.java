package com.atguigu.service;

import java.util.List;

import com.atguigu.entity.Book;
import com.atguigu.entity.Page;

public interface BookService {
	
	/**
	 * 添加图书
	 * @param book
	 * @return
	 */
	public boolean add(Book book);
	
	/**
	 * 删除图书，根据id进行删除
	 * @param book
	 * @return
	 */
	public boolean delete(Book book);
	
	/**
	 * 根据id修改某本图书
	 * @param book
	 * @return
	 */
	public boolean update(Book book);
	
	/**
	 * 查询单本图书
	 * @param book
	 * @return
	 */
	public Book getOne(Book book);
	
	/**
	 * 查询所有图书
	 * @return
	 */
	public List<Book> getAll();

	/**
	 * 按照分页查询图书
	 * @return
	 */
	public Page<Book> getPage(String pageNo, String pageSize);
	
	/**
	 * 带价格查询的分页逻辑
	 * @param pageNo
	 * @param pageSize
	 * @param min
	 * @param max
	 * @return
	 */
	public Page<Book> getByPrice(String pageNo,String pageSize,String min,String max);

}
