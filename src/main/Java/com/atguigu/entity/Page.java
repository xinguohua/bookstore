package com.atguigu.entity;

import java.util.List;

/**
 * 分页模型
 * 
 * @author lfy
 * 
 * @param <T>
 */
public class Page<T> {

	// 凡是计算得到的数据，我们可以只提供一个getter方法。在getter方法中进行计算，
	// 我们应该删除setter方法。为了数据的安全性

	private List<T> pageData;// 分页查出的数据，数据查询出数据以后调用setter方法将数据设置进入

	private int pageNo;// 当前页码，从页面传递过来，调用setter方法将PageNo设置年进入

	private int totalPage;// 总页码，是计算出来的，根据总记录数和pageSize计算的

	private int totalCount;// 总记录数，通过数据库查出出来，调用setter方法再设置进来

	private int pageSize = 4;// 每页多少条数据.

	private int index;// 数据库查询开始的索引,这个是根据第几页以及每页显示的记录数计算出来的

	private boolean hasPrev;// 是否有上一页，判断

	private boolean hasNext;// 是否有下一页，判断

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		// 要求，必须提前将pageSize和totalCount设置好
		int total = getTotalPage();
		if (pageNo > total) {
			// 设置为最后一页
			pageNo = total;
		}
		if (pageNo < 1) {
			//设置最小就是第一页
			pageNo = 1;
		}
		this.pageNo = pageNo;
	}

	/**
	 * totalPage代表总页码，需要进行计算。 计算规则； 总记录数 每页显示的条数 总页码 20 3 7 50 5 10
	 * 规则：如果不能整除，有余数，我们应该多加一页
	 * 
	 * @return
	 */
	public int getTotalPage() {
		int number = getTotalCount() / getPageSize();
		if (getTotalCount() % getPageSize() > 0) {
			number = number + 1;
		}
		return number;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 计算索引 计算规则 当前页码 每页显示的条数 索引 1 4 0 (当前页码-1)*pageSize 2 4 4 3 4 8
	 * 
	 * @return
	 */
	public int getIndex() {
		// int index = (getPageNo()-1)*getPageSize();
		return (getPageNo() - 1) * getPageSize();
	}

	public boolean isHasPrev() {
		// 当前页码大于1 有上一页
		return getPageNo() > 1;
	}

	public boolean isHasNext() {
		// 当前页码小于总页码
		return getPageNo() < getTotalPage();
	}

	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Page [pageData=" + pageData + ", pageNo=" + pageNo
				+ ", totalPage=" + totalPage + ", totalCount=" + totalCount
				+ ", pageSize=" + pageSize + ", index=" + index + ", hasPrev="
				+ hasPrev + ", hasNext=" + hasNext + "]";
	}

}
