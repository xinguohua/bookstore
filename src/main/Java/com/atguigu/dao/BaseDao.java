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

import javax.persistence.criteria.CriteriaBuilder;

/**
 * 定义基础的增删改查操作
 * 
 * @author lfy User Book
 * @param <T>
 */
public class BaseDao<T> {

	private QueryRunner runner = new QueryRunner();

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
		long id = Thread.currentThread().getId();
		System.out.println("BaseDao中的线程号："+id);
		Connection connection = JDBCUtils.getConnection();
		// ResultSetHandler
		T query = null;
		try {
			// 1、先按照指定的sql语句，从数据库中查出数据
			// 2、按照指定的类型封装成对应的对象
			// dbutils. database utils
			// 封装对象的时候要指定对象的类型
			// basedao中的基础操作被每个dao都可以使用。
			// UserDaoImpl extends BaseDao<User>
			// UserDaoImpl他们来定义操作数据表。BaseDao方便其他使用
			// 必须获取到泛型的类型 Class 一个类的类型
			//
			// ResultSetHandler<T>
			// this.getClass().getSuperclass()
			// map.put("id","1") map.put("username","1")
			query = runner.query(connection, sql, new BeanHandler<T>(type), params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} 
		return query;
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
		long id = Thread.currentThread().getId();
		System.out.println("BaseDao中的线程号："+id);
		Connection connection = JDBCUtils.getConnection();
		List<T> query = null;
		try {
			query = runner.query(connection, sql, new BeanListHandler<>(type), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//throw new Exception();
			//不能抛编译时的异常。我们可以跑运行时异常
			throw new RuntimeException();
		} 
		return query;
	}

	/*
	 * 执行增删改
	 */
	public int update(String sql, Object... params) {
		long id = Thread.currentThread().getId();
		System.out.println("BaseDao中的线程号："+id);
		Connection connection = JDBCUtils.getConnection();
		int update = 0;
		try {
			update = runner.update(connection, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();   //在控制台打印异常信息
			throw new RuntimeException();
		} 
		return update;
	}

	public Object getSingleValue(String sql, Object... params) {
		long id = Thread.currentThread().getId();
		System.out.println("BaseDao中的线程号："+id);
		Connection connection = JDBCUtils.getConnection();
		//
		Object query = null;
		try {
			// 代表查出的单个值
			query = runner.query(connection, sql, new ScalarHandler(), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//throw new RuntimeException();
			throw new RuntimeException();
		} 

		return query;
	}
}
