package com.bookstore.service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.frontend.cart.Cart;
import com.bookstore.dao.BookDAO;
import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;

public class OrderServices {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private OrderDAO orderDAO;
	private BookDAO bookDAO;

	public OrderServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.orderDAO = new OrderDAO();
		this.bookDAO = new BookDAO();
	}

	public void listAll() throws ServletException, IOException {
		listAll(null);
	}

	public void listAll(String message) throws ServletException, IOException {
		List<BookOrder> orderList = orderDAO.listAll();
		request.setAttribute("orderList", orderList);
		if (message != null) {
			request.setAttribute("message", message);
		}
		CommonUtility.forwardToPage("order_list.jsp", request, response);
	}

	public void viewDetailOrder() throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("id"));
		BookOrder order = orderDAO.get(orderId);

		if (order == null) {
			String message = "Could not find order with ID [" + orderId + "].";
			listAll(message);
		} else {
			request.setAttribute("order", order);
			CommonUtility.forwardToPage("order_detail.jsp", request, response);
		}
	}

	public void deleteOrder() throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("id"));
		BookOrder order = orderDAO.get(orderId);
		String message = null;

		if (order == null) {
			message = "Could not find order with ID [" + orderId + "].";

		} else {
			orderDAO.delete(orderId);
			message = "Order with ID [" + orderId + "] is deleted successfully.";
		}
		listAll(message);
	}

	public void showCheckoutForm() throws ServletException, IOException {
		CommonUtility.forwardToPage("frontend/checkout_form.jsp", request, response);
	}

	public void placeOrder() throws ServletException, IOException {
		System.out.println("##### OrderServices placeOrder()()");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		Customer customer = (Customer) request.getSession().getAttribute("customer");

		Set<OrderDetail> orderDetails = new HashSet<>();
		BookOrder order = new BookOrder();

		for (Entry<Book, Integer> entry : cart.getItems().entrySet()) {

			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBook(entry.getKey());
			orderDetail.setBookOrder(order);
			orderDetail.setQuantity(entry.getValue());
			float subtotal = Float.valueOf(new DecimalFormat("#.##").format(entry.getKey().getPrice() * entry.getValue()));
			orderDetail.setSubtotal(subtotal);

			orderDetails.add(orderDetail);
		}

		order.setCustomer(customer);
		order.setOrderDetails(orderDetails);
		order.setPaymentMethod(request.getParameter("payment_method"));
		order.setRecipientName(request.getParameter("recipient_name"));
		order.setRecipientPhone(request.getParameter("recipient_phone"));
		order.setShippingAddress(request.getParameter("address"));
		order.setStatus("Approved");
		order.setTotal(cart.getTotalAmount());
		
		orderDAO.create(order);
		
		cart.clear();

		CommonUtility.showMessageFrontend("Order Placed Successfully.", request, response);

	}

}
