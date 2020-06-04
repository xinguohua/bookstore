package com.atguigu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.BookDao;
import com.atguigu.entity.Book;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {

	@Override
	public int addBook(Book book) {
		// TODO Auto-generated method stub
		String sql = "insert into bs_book(title,author,price,sales,stock,img_path) " + "values(?,?,?,?,?,?)";
		return update(sql, book.getTitle(), book.getAuthor(), book.getPrice(),
				book.getSales(), book.getStock(), book.getImgPath());
	}

	@Override
	public int deleteBook(Book book) {
		// TODO Auto-generated method stub
	//	List<Object> param = new ArrayList<>();
		String sql = "delete from bs_book where id=?";
/*		if(book.getId()>0){
			sql += "id=?";
			param.add(book.getId());
		}
		if(book.getTitle()!=null){
			sql += " title=?";
			param.add(book.getTitle());
		}*/
		
		
		return update(sql, book.getId());
	}


	@Override
	public int updateBook(Book book) {
		// TODO Auto-generated method stub
		String sql="update bs_book set title=?,author=?,price=?,"+ "sales=?,stock=?,img_path=? where id=?";
		
		return update(sql, book.getTitle(),book.getAuthor(),book.getPrice(), book.getSales(),book.getStock(),book.getImgPath(),book.getId());
	}

	@Override
	public Book getOne(Book book) {
		// TODO Auto-generated method stub
		System.out.println("要查询的id："+book.getId());
		String sql = "select id,title,author,price,sales,stock,img_path imgPath from bs_book " + "where id=?";
		return getBean(sql, book.getId());
	}

	@Override
	public List<Book> getAll() {
		// TODO Auto-generated method stub
		String sql ="select id,title,author,price,sales,stock,img_path imgPath from bs_book";
		return getBeanList(sql);
	}

	@Override
	public List<Book> getBooksByPage(int index, int size) {
		// TODO Auto-generated method stub
		String sql ="select id,title,author,price,sales,stock,"
				+ "img_path imgPath from bs_book limit ?,?";
		return getBeanList(sql, index,size);
	}

	/**
	 * 返回图书的总记录数
	 */
	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		String sql ="select count(*) from bs_book";
		Object object = getSingleValue(sql);
		//int parseInt = Integer.parseInt(object.toString());
		return Integer.parseInt(object.toString());
	}

	@Override
	public List<Book> getPageByPrice(int index, int size, double min, double max) {
		// TODO Auto-generated method stub
		//带价格的分页查询sql
		String sql = "select id,title,author,price,sales,stock,"
				+ "img_path imgPath from bs_book where price between ? and ? limit ?,?";
		getBeanList(sql, min,max,index,size);
		return getBeanList(sql, min,max,index,size);
	}

	@Override
	public int getCountByPrice(double min, double max) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from bs_book where price between ? and ?";
		Object singleValue = getSingleValue(sql, min,max);
		return Integer.parseInt(singleValue.toString());
	}

	@Override
	public int updateSalesAndStock(Book book) {
		// TODO Auto-generated method stub
		String sql ="update bs_book set sales=?,stock=? where id=?";
		return update(sql, book.getSales(),book.getStock(),book.getId());
	}

	@Override
	public int updateSalesAndStock(Book book, int count) {
		// TODO Auto-generated method stub
		long id = Thread.currentThread().getId();
		System.out.println("BookDao中的线程号："+id);
		String sql ="update bs_book set sales=sales+?,stock=stock-? where id=?";
		return update(sql, count,count,book.getId());
	}
	
	

}
