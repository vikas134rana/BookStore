package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

@WebFilter("/*")
public class CommonFilter implements Filter {

	public final CategoryDAO categoryDAO;

	public CommonFilter() {
		categoryDAO = new CategoryDAO();
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

		if (!url.startsWith("/admin/")) { // ignore filter in admin servlets
			List<Category> categoryList = categoryDAO.listAll();
			request.setAttribute("categoryList", categoryList);
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
