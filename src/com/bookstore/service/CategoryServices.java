package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

public class CategoryServices {

	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
		categoryDAO = new CategoryDAO();
		this.request = request;
		this.response = response;
	}

	public void createCategory() throws ServletException, IOException {
		String message = null;
		String name = request.getParameter("categoryName");

		Category categoryExist = categoryDAO.findByName(name);

		if (categoryExist != null) {
			message = "Category[" + name + "] already exist.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} else {
			Category category = new Category(name);
			category = categoryDAO.create(category);
			message = "Category[" + name + "] created successully.";
		}
		listCategory(message);
	}

	public void editCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(categoryId);

		if (category == null) {
			request.setAttribute("message", " Could not find category with ID [" + categoryId + "]");
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}

		request.setAttribute("category", category);
		request.getRequestDispatcher("category_form.jsp").forward(request, response);
	}

	public void updateCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String name = request.getParameter("categoryName");
		String message = "";

		Category existingCategory = categoryDAO.findByName(name);

		if (existingCategory != null && existingCategory.getCategoryId() != categoryId) {
			message = "Category[" + name + "] already exist.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} else {
			Category cat = new Category(categoryId, name);
			categoryDAO.update(cat);
			message = "Category[" + name + "] updated successsfully.";
		}
		listCategory(message);
	}

	public void deleteCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category categoryExist = categoryDAO.get(categoryId);

		if (categoryExist == null) {
			String message = "Could not find category with ID[" + categoryId + "].";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);

		} else {
			categoryDAO.delete(categoryId);
			String message = "Category with id[" + categoryId + "] deleted successfully.";
			listCategory(message);
		}
	}

	public void listCategory() throws ServletException, IOException {
		listCategory(null);
	}

	public void listCategory(String message) throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);

		if (message != null)
			request.setAttribute("message", message);

		request.getRequestDispatcher("category_list.jsp").forward(request, response);
	}
}
