package com.bookstore.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookServices {

	private BookDAO bookDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private EntityManager entityManager;

	public BookServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
		this.entityManager = entityManager;
		this.request = request;
		this.response = response;
		bookDAO = new BookDAO(entityManager);
	}

	public void listBook() throws ServletException, IOException {
		listBook(null);
	}

	public void listBook(String message) throws ServletException, IOException {
		List<Book> bookList = bookDAO.listAll();
		request.setAttribute("bookList", bookList);
		if (message != null)
			request.setAttribute("message", message);
		request.getRequestDispatcher("book_list.jsp").forward(request, response);
	}

	public void createBook() throws ParseException, IOException, URISyntaxException, ServletException {
		String message = null;
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		String isbn = request.getParameter("isbn");
		float price = Float.parseFloat(request.getParameter("price"));
		Date publishDate = new SimpleDateFormat().parse(request.getParameter("publishDate"));
		byte[] image = Files.readAllBytes(Paths.get(new URI(request.getParameter("image"))));
		Category category = new CategoryDAO(entityManager).get(request.getParameter("categoryId"));

		Book book = new Book(category, title, author, description, isbn, image, price, publishDate, null);

		Book bookExist = bookDAO.findByTitle(title);

		if (bookExist == null) {
			book = bookDAO.create(book);
			message = "Book [" + title + "] created successfully.";
			listBook(message);
		} else {
			message = "Book [" + title + "] alread exists.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}

	}

	public void editBook() {

	}

	public void updateBook() {

	}

	public void deleteBook() {

	}
}
