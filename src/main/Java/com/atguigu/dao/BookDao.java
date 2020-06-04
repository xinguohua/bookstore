package com.atguigu.dao;

import java.util.List;

import com.atguigu.entity.Book;

public interface BookDao {

	/**
	 * 添加一本图书
	 * 
	 * @param book
	 * @return
	 */
	public int addBook(Book book);

	/**
	 * 删除一本图书，根据book中的id进行删除
	 * 
	 * @param book
	 * @return
	 */
	public int deleteBook(Book book);

	/**
	 * 修改图书信息
	 * 
	 * @param book
	 *            封装修改后的图书信息
	 * @return
	 */
	public int updateBook(Book book);

	/**
	 * 查出一本图书，按照id查
	 * 
	 * @param book
	 * @return
	 */
	public Book getOne(Book book);

	/**
	 * 查出所有图书
	 * 
	 * @return
	 */
	public List<Book> getAll();

	/**
	 * 分页查询图书
	 * 
	 * @param index
	 *            代表开始查询的所有
	 * @param size
	 *            代表每页的记录数
	 * @return
	 */
	public List<Book> getBooksByPage(int index, int size);

	/**
	 * 查出图书的总记录数
	 * 
	 * @return
	 */
	public int getTotalCount();

	/**
	 * 带价格的分页查询
	 * 
	 * @param index
	 * @param size
	 * @param min
	 * @param max
	 * @return
	 */
	public List<Book> getPageByPrice(int index, int size, double min, double max);
	
	/**
	 * 获取某个价格区间的总记录数
	 * @return
	 */
	public int getCountByPrice(double min,double max);
	
	/**
	 * 修改库存和销量
	 * @param book
	 * @return
	 */
	public int updateSalesAndStock(Book book);
	
	
	/**
	 * 修改库存和销量
	 * @param book
	 * @param count   修改的数量
	 * @return
	 */
	public int updateSalesAndStock(Book book,int count);

}
