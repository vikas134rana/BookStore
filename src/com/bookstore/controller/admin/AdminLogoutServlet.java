package com.bookstore.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.BaseServlet;
import com.bookstore.service.UserServices;

/**
 * Servlet implementation class AdminLogoutServlet
 */
@WebServlet("/admin/logout")
public class AdminLogoutServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public AdminLogoutServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserServices userServices = new UserServices(entityManager, request, response);
		userServices.logoutUser();
	}

}
