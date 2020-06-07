package com.atguigu.service.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.entity.User;
import com.atguigu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户相关业务逻辑的实现
 * @author lfy
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao ud;

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return ud.getUserByUserNameAndPassword(user);
	}

	@Override
	public boolean regist(User user) {
		// TODO Auto-generated method stub
		return ud.saveUser(user);
	}

	/**
	 * true：代表存在
	 * false：代表不存在
	 */
	@Override
	public boolean isExist(User user) {
		// TODO Auto-generated method stub
		return ud.getUserByUserName(user)!=null;
	}

}
