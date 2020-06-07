package com.atguigu.dao.impl;

import java.util.List;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.entity.Order;
import com.atguigu.entity.OrderItem;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {

	@Override
	public List<OrderItem> getItems(Order order) {
		// TODO Auto-generated method stub
		String sql = "select id,title,price,author,img_path imgPath,amount,"
				+ "total_money totalMoney,order_id orderId"
				+ " from bs_order_item where order_id=?";
		return getBeanList(sql, order.getId());
	}

	@Override
	public int saveItem(OrderItem item) {
		// TODO Auto-generated method stub
		long id = Thread.currentThread().getId();
		System.out.println("OrderItemImpl中的线程号："+id);
		String sql = "insert into bs_order_item(title,price,author,img_path,amount,total_money,order_id) "
				+ "values(?,?,?,?,?,?,?)";
		return update(sql, item.getTitle(),item.getPrice(),item.getAuthor(),
				item.getImgPath(),item.getAmount(),item.getTotalMoney(),item.getOrderId());
	}

}
