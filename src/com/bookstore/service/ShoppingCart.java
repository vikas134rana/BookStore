package com.bookstore.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;

public class ShoppingCart {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private BookDAO bookDao;

	public ShoppingCart(HttpServletRequest request, HttpServletResponse response) {
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

		String bookName = book.getTitle();
		float price = book.getPrice();
		int quantity = 1;
		String imageBase64 = book.getImageBase64();

		Map<Integer, CartItem> cartMap = (Map<Integer, CartItem>) request.getSession().getAttribute("cartMap");

		CartItem cartItem = new CartItem(bookId, bookName, quantity, price, imageBase64);

		if (cartMap == null) {
			cartMap = new HashMap<>();
			request.getSession().setAttribute("cartMap", cartMap);

		} else {
			if (cartMap.containsKey(bookId)) {
				cartItem = cartMap.get(bookId);
				cartItem.setQuantity(cartItem.getQuantity() + 1);
			}
		}

		cartMap.put(bookId, cartItem);

		CommonUtility.forwardToPage("view_book?id=" + bookId, request, response);

	}

	public void removeFromCart() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDao.get(bookId);
		Map<Integer, CartItem> cartMap = (Map<Integer, CartItem>) request.getSession().getAttribute("cartMap");
		cartMap.remove(bookId);
		request.setAttribute("message", "Book [" + book.getTitle() + "] removed from cart successfully");
		viewCart();
	}

	public void clearCart() throws ServletException, IOException {
		Map<Integer, CartItem> cartMap = (Map<Integer, CartItem>) request.getSession().getAttribute("cartMap");
		cartMap.clear();
		viewCart();
	}

}
