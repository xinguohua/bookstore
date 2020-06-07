package com.atguigu.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 代表每一个购物项
 * @author lfy
 *
 */
public class CartItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Book book;//代表当前购买的图书
	
	private int count;//代表当前图书购买的数量
	
	private double totalPrice;//代表当前图书的总价，图书的数量*单价

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 计算商品的价钱
	 * @return
	 */
	public double getTotalPrice() {
		//
		BigDecimal price = new BigDecimal(book.getPrice()+"");
		BigDecimal count = new BigDecimal(getCount());
		BigDecimal multiply = price.multiply(count);
		return multiply.doubleValue();
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + ", totalPrice="
				+ totalPrice + "]";
	}

	public CartItem(Book book, int count, double totalPrice) {
		super();
		this.book = book;
		this.count = count;
		this.totalPrice = totalPrice;
	}

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
