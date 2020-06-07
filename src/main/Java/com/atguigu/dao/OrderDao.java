package com.atguigu.dao;

import java.util.List;

import com.atguigu.entity.Order;
import com.atguigu.entity.User;

/**
 * 操作bs_order
 * @author lfy
 *
 */
public interface OrderDao {
	
	/**
	 * 保存订单
	 * @param order
	 * @return
	 */
	public int saveOrder(Order order);
	
	/**
	 * 查出所有订单。给管理员使用
	 * @return
	 */
	public List<Order> getAllOrders();
	
	/**
	 * 查出某个用户的所有订单
	 * @param user
	 * @return
	 */
	public List<Order> getMyOrders(User user);
	
	/**
	 * 修改订单状态
	 * @param order
	 * @return
	 */
	public int updateStatus(Order order);
}
