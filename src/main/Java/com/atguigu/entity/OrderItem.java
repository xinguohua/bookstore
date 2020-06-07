package com.atguigu.entity;

/**
 * 订单项模型
 * @author lfy
 *
 */
public class OrderItem {

	private Integer id;//主键
	
	//当时购买的图书的详细信息
	private String title;//书名
	private String author;//作者
	private String imgPath;//封面地址
	private double price;//单价
	
	
	private int amount;//购买的数量
	
	private double totalMoney;//购买的金额
	
	private String orderId;//属于哪个订单

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", title=" + title + ", author="
				+ author + ", imgPath=" + imgPath + ", price=" + price
				+ ", amount=" + amount + ", totalMoney=" + totalMoney
				+ ", orderId=" + orderId + "]";
	}
	
	
	
}
