package com.atguigu.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * web项目要使用的一些工具类
 * 
 * @author lfy
 * 
 */
public class WebUtils {

	private static ApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext.xml");

	/**
	 * 从ioc容器中获取组件
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz){

		return  ioc.getBean(clazz);
	}



	/**
	 * 将request中提交的数据自动封装为对应的对象
	 * 
	 * @param request
	 * @param t
	 * @return
	 */
	public static <T> T param2Bean(HttpServletRequest request, T t) {
		// TODO Auto-generated method stub
		// 1、获取到t中所有的属性//
		Field[] fields = t.getClass().getDeclaredFields();
		for (Field field : fields) {
			// 遍历每一个属性 获取属性名
			String name = field.getName();
			// 2、从request中获取对应的属性的值
			// 从request中获取属性对应的值
			String value = request.getParameter(name);
			// 将值封装进入
			try {
				field.setAccessible(true);
				field.set(t, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 3、将获取到的值封装为对象并返回
		return t;
	}

	/**
	 * 增强版的封装对象 要求 ：页面传值 input name=value java属性名必须和传递过来的参数名相同。
	 * 
	 * @param request
	 * @param t
	 * @return
	 */
	public static <T> T param2Bean2(HttpServletRequest request, T t) {
		// 将请求参数的map返回
		Map map = request.getParameterMap();
		try {
			BeanUtils.populate(t, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 生成订单号
	 * @param user
	 * @return
	 */
	public static String createOrderId(User user) {
		return new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())
				.toString() + user.getId();
	}
	
	/**
	 * 返回系统的当前登陆用户
	 * @param request
	 * @return
	 */
	public static User getCurrentUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		return user;
	}

}
