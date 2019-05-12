package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.time.DateUtils;

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

	public void newBook() throws ServletException, IOException {
		CategoryDAO categoryDAO = new CategoryDAO(entityManager);
		List<Category> categoryList = categoryDAO.listAll();
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("book_form.jsp").forward(request, response);
	}

	public void createBook() throws IOException, ServletException {
		String message = null;
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		String isbn = request.getParameter("isbn");
		float price = Float.parseFloat(request.getParameter("price"));

		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		Category category = new CategoryDAO(entityManager).get(categoryId);

		Date publishDate = null;
		try {
			publishDate = DateUtils.parseDate(request.getParameter("publishDate"), new String[] { "yyyy-MM-dd" });
		} catch (Exception e) {
			e.printStackTrace();
		}

		Part part = request.getPart("image");
		long size = part.getSize();
		byte[] image = new byte[(int) size];
		InputStream is = part.getInputStream();
		is.read(image);

		System.out.println("Title: " + title);
		System.out.println("Author: " + author);
		System.out.println("description: " + description);
		System.out.println("isbn: " + isbn);
		System.out.println("price: " + price);
		System.out.println("categoryId: " + categoryId);
		System.out.println("publishDate: " + publishDate);
		System.out.println("image: " + image);

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

	public void editBook() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(bookId);
		String message = null;

		if (book == null) {
			message = "Book with id [" + bookId + "] not found.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} else {
			CategoryDAO categoryDAO = new CategoryDAO(entityManager);
			List<Category> categoryList = categoryDAO.listAll();
			request.setAttribute("book", book);
			request.setAttribute("categoryList", categoryList);
			request.getRequestDispatcher("book_form.jsp").forward(request, response);
		}
	}

	public void updateBook() throws IOException, ServletException {

		String message = null;
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		String isbn = request.getParameter("isbn");
		float price = Float.parseFloat(request.getParameter("price"));

		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		Category category = new CategoryDAO(entityManager).get(categoryId);

		Date publishDate = null;
		try {
			publishDate = DateUtils.parseDate(request.getParameter("publishDate"), new String[] { "yyyy-MM-dd" });
		} catch (Exception e) {
			e.printStackTrace();
		}

		byte[] image = null;
		Part part = request.getPart("image");
		if (part.getSize() != 0) {
			long size = part.getSize();
			image = new byte[(int) size];
			InputStream is = part.getInputStream();
			is.read(image);
		} else {
			image = bookDAO.get(bookId).getImage();
		}

		System.out.println("BookId: " + bookId);
		System.out.println("Title: " + title);
		System.out.println("Author: " + author);
		System.out.println("description: " + description);
		System.out.println("isbn: " + isbn);
		System.out.println("price: " + price);
		System.out.println("categoryId: " + categoryId);
		System.out.println("publishDate: " + publishDate);
		System.out.println("image: " + image);
		System.out.println("imageSize: " + image.length);

		Book book = new Book(category, title, author, description, isbn, image, price, publishDate, null);
		book.setBookId(bookId);

		Book bookExist = bookDAO.findByTitle(title);

		if (bookExist != null && bookExist.getBookId() != bookId) {
			message = "Book [" + title + "] already exists.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} else {

			book = bookDAO.update(book);
			message = "Book [" + title + "] updated successfully.";
			listBook(message);
		}

	}

	public void deleteBook() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(bookId);
		String message = null;

		if (book == null) {
			message = "Book with id [" + bookId + "] not found.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} else {
			message = "Book with id [" + bookId + "] deleted successfully.";
			request.setAttribute("message", message);
			bookDAO.delete(bookId);
			listBook();
		}
	}

}
