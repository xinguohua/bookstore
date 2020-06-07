package com.atguigu.service;


import java.util.List;

import com.atguigu.entity.Order;
import com.atguigu.entity.OrderItem;

public interface OrderItemService {

	/**
	 * 保存某个订单项
	 * 
	 * @param item
	 */
	public void saveItem(OrderItem item);
	
	/**
	 * 查出订单对应的所有订单项
	 * @param order
	 * @return
	 */
/*	public List<OrderItem> getItems(Order order);*/

}
