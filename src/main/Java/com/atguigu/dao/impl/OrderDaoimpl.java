package com.atguigu.dao.impl;

import java.util.List;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.entity.Order;
import com.atguigu.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoimpl extends BaseDao<Order> implements OrderDao{

	@Override
	public int saveOrder(Order order) {
		long id = Thread.currentThread().getId();
		System.out.println("OrderDaoimpl中的线程号："+id);
		// TODO Auto-generated method stub
		String sql = "insert into bs_order(id,create_date,total_money,status,userid) "
				+ "values(?,?,?,?,?)";
		
		return update(sql, order.getId(),order.getCreateDate(),
				order.getTotalMoney(),order.getStatus(),order.getUserid());
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		String sql = "select id,create_date createDate,total_money totalMoney,"
				+ "status,userid from bs_order";
		return getBeanList(sql);
	}

	@Override
	public List<Order> getMyOrders(User user) {
		// TODO Auto-generated method stub
		String sql = "select id,create_date createDate,total_money totalMoney,"
				+ "status,userid from bs_order where userid=?";
		return getBeanList(sql, user.getId());
	}

	@Override
	public int updateStatus(Order order) {
		// TODO Auto-generated method stub
		String sql = "update bs_order set status=? where id=?";
		return update(sql, order.getStatus(),order.getId());
	}

}
