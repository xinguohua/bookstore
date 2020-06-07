package com.atguigu.dao;

import java.util.List;

import com.atguigu.entity.Order;
import com.atguigu.entity.OrderItem;

/**
 * 操作bs_order_item
 * @author lfy
 *
 */
public interface OrderItemDao {
	
	/**
	 * 查出某个订单对应的所有订单项
	 * @param order
	 * @return
	 */
	public List<OrderItem> getItems(Order order);
	
	/**
	 * 保存某个订单项
	 * @param item
	 * @return
	 */
	public int saveItem(OrderItem item);
}
