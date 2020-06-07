package com.atguigu.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.OrderDaoimpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.entity.Book;
import com.atguigu.entity.Cart;
import com.atguigu.entity.CartItem;
import com.atguigu.entity.Order;
import com.atguigu.entity.OrderItem;
import com.atguigu.entity.User;
import com.atguigu.service.OrderService;
import com.atguigu.utils.JDBCUtils;
import com.atguigu.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao od ;
	@Autowired
	private OrderItemDao oid ;
	@Autowired
	private BookDao bd = new BookDaoImpl();

	@Override
	public List<Order> getMyOrders(User user) {
		// TODO Auto-generated method stub
		return od.getMyOrders(user);
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return od.getAllOrders();
	}

	@Override
	public void recevied(Order order) {
		// TODO Auto-generated method stub
		// 页面只需要传递一个订单id过来不
		// 订单状态赋值为确认收货（已完成）
		order.setStatus(2);
		// 数据库修改状态
		od.updateStatus(order);
	}

	@Override
	public void send(Order order) {
		// TODO Auto-generated method stub
		order.setStatus(1);
		od.updateStatus(order);
	}

	@Override
	public List<OrderItem> getOrderInfo(Order order) {
		// TODO Auto-generated method stub

		return oid.getItems(order);
	}


	//@Transactional
	@Override
	public String checkout(User user, Cart cart) {
		// TODO Auto-generated method stub
		long id = Thread.currentThread().getId();
		System.out.println("service中的线程号：" + id);
		// 1）、生成订单号 时间戳+用户id long 初始化订单信息
		String orderId = null;

		// 把整个购物车中的东西转化为订单。属于订单对象的放在order中，属于订单项的放在订单项中
		// 1、生成一个订单对象。保存到数据库
		Order order = new Order();
		orderId = WebUtils.createOrderId(user);
		order.setId(orderId);// 设置订单号
		order.setCreateDate(new Date());// 设置订单日期
		order.setTotalMoney(cart.getTotalMoney());// 设置订单总额
		order.setUserid(user.getId());

		// 2）、保存订单信息 bs_order保存订单-orderItem中保存每一个订单项，去bs_book中修改库存和销量
		// 注意：一定是先保存订单再保存订单项
		od.saveOrder(order);

		// 2、购物车中的每一项应该也封装成对应的订单项。保存到数据库
		// 购物车中的每个购物项都是一个订单项
		List<CartItem> items = cart.getAllItems();
		for (CartItem item : items) {
			OrderItem orderItem = new OrderItem();
			// 初始化orderItem信息
			orderItem.setAmount(item.getCount());// 购买的数量
			orderItem.setAuthor(item.getBook().getAuthor());// 作者信息
			orderItem.setImgPath(item.getBook().getImgPath());// 图片封面
			orderItem.setOrderId(orderId);// 关联的订单号
			orderItem.setPrice(item.getBook().getPrice());// 图书单价
			orderItem.setTitle(item.getBook().getTitle());// 图书名
			orderItem.setTotalMoney(item.getTotalPrice());// 每项的总额

			// 保存订单项
			oid.saveItem(orderItem);

			// 3、修改图书表中的库存和销量 要修改的图书
			// 应该从数据库实时的获取图书，不能从购物车中取图书
			// Book book = item.getBook();
			// 从数据库获取购物车中图书的实时信息
			/*
			 * Book book = bd.getOne(item.getBook()); //现在的销量加上购买的数量
			 * book.setSales(book.getSales()+item.getCount()); //现在的库存减去购买的数量
			 * book.setStock(book.getStock()-item.getCount()); //修改库存和销量
			 * bd.updateSalesAndStock(book);
			 */

			// 数据库底层来修改库存和销量
			bd.updateSalesAndStock(item.getBook(), item.getCount());
		}
		// 4、清空购物车
		cart.clear();

		return orderId;
	}

}
