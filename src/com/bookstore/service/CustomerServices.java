package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.HashGenerator;
import com.bookstore.entity.Customer;

public class CustomerServices {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private CustomerDAO customerDAO;

	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		customerDAO = new CustomerDAO();
	}

	public void listAll() throws ServletException, IOException {
		listAll(null);
	}

	public void listAll(String message) throws ServletException, IOException {
		List<Customer> customerList = customerDAO.listAll();
		request.setAttribute("customerList", customerList);
		if (message != null)
			request.setAttribute("message", message);
		CommonUtility.forwardToPage("customer_list.jsp", request, response);
	}

	private void setCustomer(Customer customer) {

		System.out.println("**************** setCustomer****************");

		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String zipcode = request.getParameter("zipcode");

		if (password != null && !password.isEmpty()) {
			String encryptedPassword = HashGenerator.generateSHA256(password);
			customer.setPassword(encryptedPassword);
		}

		if (email != null && !email.isEmpty())
			customer.setEmail(email);

		customer.setFullName(fullName);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setCountry(country);
		customer.setPhone(phone);
		customer.setZipcode(zipcode);
	}

	public void createCustomer() throws ServletException, IOException {

		Customer customer = new Customer();
		setCustomer(customer);

		String email = customer.getEmail();

		Customer existedcustomer = customerDAO.findByEmail(email);

		if (existedcustomer == null) {
			customerDAO.create(customer);
			String message = "Customer [" + customer.getFullName() + "] is created successfully]";
			listAll(message);
		} else {
			String message = "Cannot create Customer. Customer with email [" + customer.getEmail() + "] already exists.]";
			CommonUtility.showMessageBackend(message, request, response);
		}

	}

	public void editCustomer() throws ServletException, IOException {
		int customerId = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDAO.get(customerId);

		if (customer == null) {
			String message = "Customer with id [" + customerId + "] not found";
			CommonUtility.showMessageBackend(message, request, response);
		} else {
			request.setAttribute("customer", customer);
			CommonUtility.forwardToPage("customer_form.jsp", request, response);
		}
	}

	public void updateCustomer() throws ServletException, IOException {

		Customer customer = new Customer();

		int customerId = Integer.parseInt(request.getParameter("customerId"));
		customer.setCustomerId(customerId);
		customer.setRegisterOn(customerDAO.get(customerId).getRegisterOn());

		setCustomer(customer);

		Customer customerByEmail = customerDAO.findByEmail(customer.getEmail());

		if (customerByEmail != null && customerByEmail.getCustomerId() != customerId) {

			String message = "Customer with email [" + customer.getEmail() + "] already exist.";
			CommonUtility.showMessageBackend(message, request, response);

		} else {

			String password = customer.getPassword();
			String encryptedPassord = null;

			if (password == null) {
				encryptedPassord = customerDAO.get(customerId).getPassword();
				customer.setPassword(encryptedPassord);
			}

			customerDAO.update(customer);
			String message = "Customer [" + customer.getFullName() + "] already exist.";
			listAll(message);
		}

	}

	public void deleteCustomer() throws ServletException, IOException {

		int customerId = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDAO.get(customerId);
		String message = null;

		if (customer == null) {
			message = "Customer with id [" + customerId + "] not found.";
			CommonUtility.showMessageBackend(message, request, response);
		} else {

			if (!customer.getReviews().isEmpty()) {
				message = "Could not delete customer with ID [" + customerId + "] because he/she posted reviews for books";
				CommonUtility.showMessageBackend(message, request, response);
			} else {

				message = "Customer with id [" + customerId + "] deleted successfully.";
				request.setAttribute("message", message);
				customerDAO.delete(customerId);
				listAll(message);

			}
		}

	}

	public void registerCustomer() throws ServletException, IOException {
		Customer customer = new Customer();
		setCustomer(customer);
		String message = null;

		String email = customer.getEmail();

		Customer existedcustomer = customerDAO.findByEmail(email);

		if (existedcustomer == null) {
			customerDAO.create(customer);
			message = "Customer [" + customer.getFullName() + "] is created successfully]";
		} else {
			message = "Cannot create Customer. Customer with email [" + customer.getEmail() + "] already exists.]";
		}
		CommonUtility.showMessageFrontend(message, request, response);
	}

	public void showLoginForm() throws ServletException, IOException {
		CommonUtility.forwardToPage("frontend/login.jsp", request, response);
	}

	public void doLogin() throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String encryptedPassword = HashGenerator.generateSHA256(password);

		boolean loginStatus = customerDAO.checkLogin(email, encryptedPassword);

		if (loginStatus) {
			Customer customer = customerDAO.findByEmail(email);
			request.getSession().setAttribute("customer", customer);

			if (request.getSession().getAttribute("lastRequestedUrl") != null) {
				String requestedUrl = (String) request.getSession().getAttribute("lastRequestedUrl");
				request.getSession().removeAttribute("lastRequestedUrl");
				response.sendRedirect(requestedUrl);
			} else {
				showProfile();
			}
		} else {
			String message = "Please provide valid credentials";
			CommonUtility.forwardToPage("frontend/login.jsp", message, request, response);
		}

	}

	public void logout() throws ServletException, IOException {
		request.getSession().removeAttribute("customer");
		CommonUtility.forwardToPage("frontend/index.jsp", request, response);
	}

	public void showProfile() throws ServletException, IOException {
		CommonUtility.forwardToPage("frontend/view_profile.jsp", request, response);
	}

	public void editProfile() throws ServletException, IOException {
		CommonUtility.forwardToPage("frontend/edit_profile.jsp", request, response);
	}

	public void updateProfile() throws ServletException, IOException {
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		setCustomer(customer);
		System.out.println("Updated Password : " + customer.getPassword());
		showProfile();
	}

}
