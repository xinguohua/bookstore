package com.atguigu.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/***
 * 代表整个购物车。将对购物车的操作方法也可以直接放在Cart里面
 * 
 * @author lfy
 * 
 */
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 所有的购物项都保存在这个里面
	 */
	private Map<Integer, CartItem> items = new LinkedHashMap<>();

	private int totalCount;// 商品的总数量.将所有购物项的数量相加

	private double totalMoney;// 商品的总金额，将所有购物项的总价相加

	/**
	 * Cart对象.allItems; 返回购物车中所有的购物项，把他做成一个List方便进行遍历
	 * 
	 * @return
	 */
	public List<CartItem> getAllItems() {
		Collection<CartItem> values = items.values();
		return new ArrayList<>(values);
	}

	public Map<Integer, CartItem> getItems() {
		return items;
	}

	public void setItems(Map<Integer, CartItem> items) {
		this.items = items;
	}

	/**
	 * 获取商品的总数量
	 * 
	 * @return
	 */
	public int getTotalCount() {

		List<CartItem> list = getAllItems();
		int count = 0;
		for (CartItem cartItem : list) {
			// 将每个购物项的数量相加
			count += cartItem.getCount();
		}
		return count;
	}

	/**
	 * 获取所有商品的总价格
	 * 
	 * @return
	 */
	public double getTotalMoney() {
		List<CartItem> list = getAllItems();
//		double money = 0.0;
		BigDecimal money = new BigDecimal("0.0");
		for (CartItem cartItem : list) {
			money = money.add(new BigDecimal(cartItem.getTotalPrice()+""));
		}
		return money.doubleValue();
	}

	public Cart(Map<Integer, CartItem> items, int totalCount, double totalMoney) {
		super();
		this.items = items;
		this.totalCount = totalCount;
		this.totalMoney = totalMoney;
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Cart [items=" + items + ", totalCount=" + totalCount
				+ ", totalMoney=" + totalMoney + "]";
	}

	/**
	 * 将图书添加到购物车
	 * 
	 * @param book
	 */
	public void addBook2Cart(Book book) {
		// 1、先判断map中是否有对应的购物项。
		CartItem item = items.get(book.getId());
		if (item == null) {
			// 没有购物项
			CartItem cartItem = new CartItem();
			// 设置图书
			cartItem.setBook(book);
			cartItem.setCount(1);
			// 将购物项放到map中
			items.put(book.getId(), cartItem);
		} else {
			// 如果是以后添加的，只需要数量加1即可
			item.setCount(item.getCount() + 1);
		}
	}

	/**
	 * 删除某个购物项
	 * @param bookid
	 */
	public void deleteItem(String bookid) {
		int id = 0;
		try {
			id = Integer.parseInt(bookid);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		items.remove(id);
	}
	
	/**
	 * 修改某个购物项的数量
	 * @param bookid
	 * @param count
	 */
	public void updateCount(String bookid,String count){
		int id = 0;
		try {
			id = Integer.parseInt(bookid);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取到对应的购物项
		CartItem cartItem = items.get(id);
		
		
		//转换失败还是原来的值
		int cn = cartItem.getCount();
		try {
			cn = Integer.parseInt(count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//修改数量
		cartItem.setCount(cn);
	}
	
	/**
	 * 清空购物车
	 */
	public void clear(){
		//将map清空
		items.clear();
	}
	
	/**
	 * 获取某个购物项的方法
	 * @return
	 */
	public CartItem getItem(String bid){
		int id = Integer.parseInt(bid);
		return items.get(id);
	}
	

}
