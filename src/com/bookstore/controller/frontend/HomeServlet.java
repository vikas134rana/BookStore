package com.bookstore.controller.frontend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.BookServices;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*- CategoryDAO categoryDAO = new CategoryDAO(entityManager);
		List<Category> categoryList = categoryDAO.listAll();
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("frontend/index.jsp").forward(request, response);*/
		BookServices bookServices = new BookServices(request, response);
		bookServices.listNewBooks();
	}

}
