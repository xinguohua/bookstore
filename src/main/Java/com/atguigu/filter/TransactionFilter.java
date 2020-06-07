package com.atguigu.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.utils.JDBCUtils;

/**
 * Servlet Filter implementation class TransactionFilter
 */
public class TransactionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TransactionFilter() {
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

		
		
		try {
			chain.doFilter(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("filter捕获到异常了...");
			try {
				HttpServletResponse resp = (HttpServletResponse) response;
				HttpServletRequest req = (HttpServletRequest) request;
				resp.sendRedirect(req.getContextPath()+"/pages/cart/checkerror.jsp");
			} catch (Exception e1) {
			}
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
