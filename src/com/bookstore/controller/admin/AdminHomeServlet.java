package com.bookstore.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.OrderDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.dao.UserDAO;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Review;

/**
 * Servlet implementation class AdminHomeServlet
 */
@WebServlet("/admin/")
public class AdminHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	OrderDAO orderDAO;
	ReviewDAO reviewDAO;
	UserDAO userDAO;
	BookDAO bookDAO;
	CustomerDAO customerDAO;

	public AdminHomeServlet() {
		super();
		orderDAO = new OrderDAO();
		reviewDAO = new ReviewDAO();
		userDAO = new UserDAO();
		bookDAO = new BookDAO();
		customerDAO = new CustomerDAO();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<BookOrder> recentSales = orderDAO.recentSales();
		List<Review> recentReviews = reviewDAO.recentReviews();

		long userCount = userDAO.count();
		long bookCount = bookDAO.count();
		long customerCount = customerDAO.count();
		long reviewCount = reviewDAO.count();
		long orderCount = orderDAO.count();

		request.setAttribute("recentSales", recentSales);
		request.setAttribute("recentReviews", recentReviews);

		request.setAttribute("userCount", userCount);
		request.setAttribute("bookCount", bookCount);
		request.setAttribute("customerCount", customerCount);
		request.setAttribute("reviewCount", reviewCount);
		request.setAttribute("orderCount", orderCount);

		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
