package com.atguigu.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.UserDao;
import com.atguigu.entity.User;
import com.atguigu.utils.JDBCUtils;
import org.springframework.stereotype.Repository;

/**
 * UserDao的实现类
 * 
 * @author lfy
 * 
 */
@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao {

	private QueryRunner runner = new QueryRunner();
	@Override
	public User getUserByUserNameAndPassword(User user) {
		// TODO Auto-generated method stub
		String sql = "select * from bs_user where username=? and password=?";
		//查询对应的用户并返回
		//runner.query(JDBCUtils.getConnection(), sql, new BeanHandler<>(User.class), user.getUsername(),user.getPassword());
		return getBean(sql, user.getUsername(),user.getPassword());
	}

	/**
	 * 保存成功返回true
	 */
	@Override
	public boolean saveUser(User user) {
		// TODO Auto-generated method stub
		String sql = "insert into bs_user(username,password,email) values(?,?,?)";
		int update = update(sql, user.getUsername(),user.getPassword(),user.getEmail());
		//如果数据库中没有保存，返回影响0行
		//update>0   true
		//
		return update>0;
	}

	@Override
	public User getUserByUserName(User user) {
		// TODO Auto-generated method stub
		String sql = "select * from bs_user where username=?";
		return getBean(sql, user.getUsername());
	}

}
