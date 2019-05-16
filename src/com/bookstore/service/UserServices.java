package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.HashGenerator;
import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UserServices {

	private UserDAO usersDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public UserServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		usersDAO = new UserDAO();
	}

	public void listUser() throws ServletException, IOException {
		listUser(null);
	}

	public void listUser(String message) throws ServletException, IOException {
		List<Users> listUser = usersDAO.listAll();
		request.setAttribute("listUser", listUser);

		if (message != null)
			request.setAttribute("message", message);

		request.getRequestDispatcher("user_list.jsp").forward(request, response);
	}

	public void createUser() throws ServletException, IOException {
		String email = request.getParameter("email");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String enccryptedPassword = HashGenerator.generateSHA256(password);

		Users userExist = usersDAO.findByEmail(email);

		if (userExist != null) {

			String message = "Error: Can't Create User. User with email (" + email + ") already exist.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);

		} else {
			Users user = new Users(email, userName, enccryptedPassword);
			usersDAO.create(user);
			listUser("New User (" + userName + ") created successfully.");
		}
	}

	public void editUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		Users user = usersDAO.get(userId);

		if (user == null) {

			String message = "Error: Could not find user with ID [" + userId + "]";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}

		request.setAttribute("user", user);
		request.getRequestDispatcher("user_form.jsp").forward(request, response);
	}

	public void updateUser() throws ServletException, IOException {

		int userId = Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		System.out.println("######## Update User ");
		System.out.println(userId + "\t" + userName + "\t" + email + "\t" + password);

		Users userByEmail = usersDAO.findByEmail(email);
		Users userById = usersDAO.get(userId);

		if (userById == null) {

			String message = "Error: Could not find user with ID [" + userId + "]";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}

		if (userByEmail != null && userById.getUserId() != userByEmail.getUserId()) {

			String message = "Error: Can't Update User. User with email (" + email + ") already exist.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);

		} else {

			String encryptedPassword = "";
			if (password.isEmpty()) {
				encryptedPassword = userById.getPassword();
			} else {
				encryptedPassword = HashGenerator.generateSHA256(password);
			}

			Users user = new Users(userId, email, userName, encryptedPassword);
			usersDAO.update(user);
			listUser("Updated User UserID(" + userId + ").");
		}
	}

	public void deleteUser() throws ServletException, IOException {
		String message = null;
		int userId = Integer.parseInt(request.getParameter("id"));

		int defaultUserId = 3;

		if (userId == defaultUserId) {
			message = "The default admin user account cannot be deleted";
		} else {

			Users user = usersDAO.get(userId);

			if (user == null) {
				message = "Error: Could not find user with ID [" + userId + "], or it might have been deleted by another admin.";
			} else {
				usersDAO.delete(userId);
				message = "User with ID [" + userId + "] deleted successfully.";
			}
		}
		listUser(message);
	}

	public void loginUser() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String encryptedPassword = HashGenerator.generateSHA256(password);

		boolean loginStatus = usersDAO.checkLogin(email, encryptedPassword);

		if (loginStatus) {
			System.out.println("Login Successfull");
			HttpSession session = request.getSession();
			session.setAttribute("user_email", email);
			request.getRequestDispatcher("/admin/").forward(request, response);
		} else {
			String message = "Login Failed";
			request.setAttribute("message", message);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	public void logoutUser() throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("user_email");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

}
