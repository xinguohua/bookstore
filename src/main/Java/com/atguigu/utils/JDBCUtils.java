package com.atguigu.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 获取数据库连接和关闭数据库链接
 * @author lfy
 *
 *c3p0数据库连接池技术
 *1、导包
 *c3p0-0.9.1.2.jar、
 *mysql-connector-java-5.1.7-bin.jar、
 *commons-dbutils-1.3.jar
 */
public class JDBCUtils {
	
	//创建一个链接池
	private static DataSource ds = new ComboPooledDataSource("webDataSource");
	//private static Connection conn = ds.getConnection();
	//保存了当前线程对应的connection
	//private static Map<Long,Connection> map = new HashMap<>();
	
	//在同一线程之间保存数据
	private static ThreadLocal<Connection> local = new ThreadLocal<>();
	/**
	 * 获取数据库连接
	 * 
	 * 获取当前线程对应的连接.
	 * @return
	 */
	public static  Connection getConnection(){
		
		
		long id = Thread.currentThread().getId();
		System.out.println("JDBCUtils中的线程号："+id);
		//Connection connection = null;
		/*try {
			connection = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//1、获取当前线程对应的连接
		Connection connection = null;
		try {
			//connection = map.get(id);
			//保存同一线程的数据
			//从当前线程中获取一个connection
			connection = local.get();
			//如果当前线程没有对应的连接
			if(connection==null){
				//从连接池中拿一个
				connection = ds.getConnection();
				//map.put(id, connection);
				
				//给当前线程中保存一个connection
				local.set(connection);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  connection ;
	}
	
	/**
	 * 关闭连接
	 * @param connection
	 */
	public static  void close(Connection connection){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static  void close(){
		try {
			//Connection connection = map.get(Thread.currentThread().getId());
			Connection connection = local.get();
			connection.close();
			//移除map中的对应线程号
			//map.remove(Thread.currentThread().getId());
			//从线程中移除connection
			local.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
