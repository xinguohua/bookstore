package com.atguigu.entity;

import java.util.Date;

/**
 * 订单模型
 * 
 * @author lfy
 * 
 */
public class Order {

	private String id;// 订单号

	private Date createDate;// 创建日期

	private double totalMoney;// 订单总额

	private int status = 0;// 订单状态
	//0-未发货    1-已发货    2-已完成
	
	private Integer userid;//关联哪个用户

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Order(String id, Date createDate, double totalMoney, int status,
			Integer userid) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.totalMoney = totalMoney;
		this.status = status;
		this.userid = userid;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", createDate=" + createDate
				+ ", totalMoney=" + totalMoney + ", status=" + status
				+ ", userid=" + userid + "]";
	}
	
	

}
