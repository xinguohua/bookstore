package com.atguigu.dao;

import com.atguigu.entity.User;

/**
 * 定义UserDao接口的方法
 * @author lfy
 *
 */
public interface UserDao {
	
	/*
	 * 根据传入的用户名密码查询用户
	 */
	User getUserByUserNameAndPassword(User user);
	
	boolean saveUser(User user);
	
	/**
	 * 根据用户名获取用户
	 * @param user
	 * @return
	 */
	User getUserByUserName(User user);
}
