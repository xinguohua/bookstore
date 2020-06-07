package com.atguigu.service;

import java.util.List;

import com.atguigu.entity.Cart;
import com.atguigu.entity.Order;
import com.atguigu.entity.OrderItem;
import com.atguigu.entity.User;

/**
 * 订单的业务模型
 * @author lfy
 *
 */
public interface OrderService {
	
	/**
	 * 获取某个用户对应的订单
	 * @param user
	 * @return
	 */
	public List<Order> getMyOrders(User user);
	
	/**
	 * 获取所有订单
	 * @return
	 */
	public List<Order> getAllOrders();
	
	/**
	 * 确认收货
	 * @param order
	 */
	public void recevied(Order order);
	
	/**
	 * 发货
	 * @param order
	 */
	public void send(Order order);
	
	/**
	 * 订单详情
	 * @param order
	 * @return
	 */
	public List<OrderItem> getOrderInfo(Order order);
	
	/**
	 * 结账
	 * @param user
	 * @param cart
	 * @return
	 */
	public String checkout(User user, Cart cart);
	
}
