package com.atguigu.service;

import com.atguigu.entity.User;

/**
 * 定义用户相关的业务逻辑
 * @author lfy
 *
 */
public interface UserService {
	
	/**
	 * 用户登陆逻辑
	 * @param user
	 * @return
	 */
	public User login(User user);
	
	/**
	 * 用户注册逻辑
	 * @param user
	 * @return
	 */
	public boolean regist(User user);
	
	/**
	 * 检测用户是否存在
	 * @return
	 */
	public boolean isExist(User user);

}
