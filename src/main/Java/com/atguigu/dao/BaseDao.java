package com.atguigu.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.utils.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;



/**
 * 定义基础的增删改查操作
 * 
 * @author lfy User Book
 * @param <T>
 */
public class BaseDao<T> {

	//private QueryRunner runner = new QueryRunner();
	@Autowired
	private JdbcTemplate jdbcTemplate;
	// T User com.atguigu.bean.User
	private Class<T> type;

	public BaseDao() {
			// super();
			// 子类继承BaseDao
			// 获取子类类型，获取到父类的类型
			ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
			// ParameterizedTypeImpl类是一个有参数的类型，代表的就是类的声明有泛型
			System.out.println("获取的父类的类型：" + genericSuperclass.getClass());
			// genericSuperclass.getActualTypeArguments获取真实的参数类型
			// 获取到的都是Type
			// Type type2 = genericSuperclass.getActualTypeArguments()[0];
			type = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
			// System.out.println("获取到的泛型的类型："+type);

	}

	/**
	 * 查询一条数据的方法
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public T getBean(String sql, Object... params) {
		T object=null;
		try {
			object=jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(type),params);

		}catch (DataAccessException e){
			e.printStackTrace();

		}
		return  object;
	}

	/**
	 * 获取一组数据
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public List<T> getBeanList(String sql, Object... params)  {
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(type),params);
	}

	/*
	 * 执行增删改
	 */
	public int update(String sql, Object... params) {
		int update = jdbcTemplate.update(sql, params);
		return update;
	}

	public Object getSingleValue(String sql, Object... params) {
		Object object = jdbcTemplate.queryForObject(sql, Object.class, params);

		return object;
	}
}
