package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.service.CommonUtility;

@WebFilter("/*")
public class LoginCustomerFilter implements Filter {

	private List<String> loginRequiredUrls = Arrays.asList("/view_profile", "/edit_profile", "/update_profile","/write_review");

	public LoginCustomerFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);

		String currentUrl = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		boolean loggedIn = session != null && session.getAttribute("customer") != null;
		boolean adminRequest = currentUrl.startsWith("/admin/");

		if (!loggedIn && !adminRequest && isLoginRequired(currentUrl)) {
			String requestedUrl = httpRequest.getRequestURI();
			
			if(httpRequest.getQueryString() != null) {
				requestedUrl = requestedUrl +"?"+httpRequest.getQueryString();
			}
			
			httpRequest.getSession().setAttribute("lastRequestedUrl", requestedUrl);
			
			CommonUtility.forwardToPage("login", httpRequest, httpResponse);
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean isLoginRequired(String currentUrl) {
		for (String url : loginRequiredUrls) {
			if (currentUrl.startsWith(url)) {
				return true;
			}
		}
		return false;
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
