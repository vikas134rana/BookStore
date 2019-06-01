package com.bookstore.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.frontend.cart.Cart;
import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;

public class CartServices {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private BookDAO bookDao;

	public CartServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.bookDao = new BookDAO();
	}

	public void viewCart() throws ServletException, IOException {
		CommonUtility.forwardToPage("frontend/cart_view.jsp", request, response);
	}

	public void addToCart() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDao.get(bookId);

		Cart cart = (Cart) request.getSession().getAttribute("cart");

		if (cart == null) {
			cart = new Cart();
			cart.addItem(book);
			request.getSession().setAttribute("cart", cart);

		} else {
			cart.addItem(book);
		}

		CommonUtility.forwardToPage("view_book?id=" + bookId, request, response);

	}

	public void removeFromCart() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDao.get(bookId);
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.removeItem(book);
		request.setAttribute("message", "Book [" + book.getTitle() + "] removed from cart successfully");
		viewCart();
	}

	public void clearCart() throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.clear();
		viewCart();
	}

	public void updateCart() throws ServletException, IOException {

		String[] bookIdArray = request.getParameterValues("bookId");
		int[] bookIds = new int[bookIdArray.length];
		int[] quantities = new int[bookIdArray.length];

		Cart cart = (Cart) request.getSession().getAttribute("cart");

		for (int i = 0; i < cart.getSize(); i++) {
			quantities[i] = Integer.parseInt(request.getParameter("quantity" + (i + 1)).trim());
			bookIds[i] = Integer.parseInt(bookIdArray[i]);
		}

		cart.updateCart(bookIds, quantities);
		request.setAttribute("message", "Book Quantities updated successfully");
		viewCart();
	}

}
