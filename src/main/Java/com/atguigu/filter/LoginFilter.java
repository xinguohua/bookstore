package com.atguigu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.atguigu.entity.User;
import com.atguigu.utils.WebUtils;

/**
 * Servlet Filter implementation class LoginFilter
 * 
 * 验证订单相关的操作，用户是否登陆了
 */
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		
		//1、验证用户是否登陆，如果登陆放行请求。否则回到login=页面提示登陆
		HttpServletRequest req = (HttpServletRequest) request;
		//获取当前登陆的用户
		User user = WebUtils.getCurrentUser(req);
		if(user==null){
			//用户未登录
			request.setAttribute("msg", "此操作需要登陆，请先登陆！");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(
					request, response);
		}else{
			chain.doFilter(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
