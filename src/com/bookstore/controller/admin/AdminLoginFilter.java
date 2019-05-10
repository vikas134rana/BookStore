package com.bookstore.controller.admin;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AdminLoginFilter
 */
@WebFilter("/admin/*")
public class AdminLoginFilter implements Filter {

	public AdminLoginFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = ((HttpServletRequest) request);
		HttpSession session = httpRequest.getSession(false);

		String requestUri = httpRequest.getRequestURI();
		String contextPath = request.getServletContext().getContextPath();

		boolean loginRequest = requestUri.equals(contextPath + "/admin/login");
		boolean loginPage = requestUri.equals(contextPath + "/admin/login.jsp");
		boolean loggedIn = session != null && session.getAttribute("user_email") != null;

		if (loggedIn && (loginPage || loginRequest)) {
			httpRequest.getRequestDispatcher("/admin/").forward(httpRequest, response);
		} else if (loggedIn || loginRequest)
			chain.doFilter(request, response);
		else {
			httpRequest.getRequestDispatcher("login.jsp").forward(httpRequest, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
