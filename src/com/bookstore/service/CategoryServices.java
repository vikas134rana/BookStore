package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
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
			CommonUtility.showMessageFrontend(message, request, response);
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
			String message = "Could not find category with ID [" + categoryId + "]";
			CommonUtility.showMessageBackend(message, request, response);
		}

		request.setAttribute("category", category);
		CommonUtility.forwardToPage("category_form.jsp", request, response);
	}

	public void updateCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String name = request.getParameter("categoryName");
		String message = "";

		Category existingCategory = categoryDAO.findByName(name);

		if (existingCategory != null && existingCategory.getCategoryId() != categoryId) {
			message = "Category[" + name + "] already exist.";
			CommonUtility.showMessageBackend(message, request, response);
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
			CommonUtility.showMessageBackend(message, request, response);
		} else {

			BookDAO bookDAO = new BookDAO();
			long countBook = bookDAO.countBookByCategory(categoryId);

			String message = null;
			if (countBook > 0) {
				message = "Cannot delete Category with id [" + categoryId + "] because some books are associated with this category";
			} else {
				categoryDAO.delete(categoryId);
				message = "Category with id[" + categoryId + "] deleted successfully.";
			}
			listCategory(message);
		}
	}

	public void listCategory() throws ServletException, IOException {
		listCategory(null);
	}

	public void listCategory(String message) throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		CommonUtility.forwardToPage("category_list.jsp", message, request, response);
	}
}
