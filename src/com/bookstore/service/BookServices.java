package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

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
	private CategoryDAO categoryDAO;

	public BookServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		bookDAO = new BookDAO();
		categoryDAO = new CategoryDAO();
	}

	public void listBook() throws ServletException, IOException {
		listBook(null);
	}

	public void listBook(String message) throws ServletException, IOException {
		List<Book> bookList = bookDAO.listAll();
		request.setAttribute("bookList", bookList);
		CommonUtility.forwardToPage("book_list.jsp", message, request, response);
	}

	public void newBook() throws ServletException, IOException {
		List<Category> categoryList = categoryDAO.listAll();
		request.setAttribute("categoryList", categoryList);
		CommonUtility.forwardToPage("book_form.jsp", request, response);
	}

	public void createBook() throws IOException, ServletException {
		String message = null;
		String title = request.getParameter("title");

		Book book = new Book();
		setBook(book);

		Book bookExist = bookDAO.findByTitle(title);

		if (bookExist == null) {
			book = bookDAO.create(book);
			message = "Book [" + title + "] created successfully.";
			listBook(message);
		} else {
			message = "Book [" + title + "] alread exists.";
			CommonUtility.showMessageBackend(message, request, response);
		}

	}

	public void editBook() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(bookId);
		String message = null;

		if (book == null) {
			message = "Book with id [" + bookId + "] not found.";
			CommonUtility.showMessageBackend(message, request, response);
		} else {
			List<Category> categoryList = categoryDAO.listAll();
			request.setAttribute("book", book);
			request.setAttribute("categoryList", categoryList);
			CommonUtility.forwardToPage("book_form.jsp", request, response);
		}
	}

	public void updateBook() throws IOException, ServletException {

		String message = null;
		Book book = new Book();

		setBook(book);

		int bookId = Integer.parseInt(request.getParameter("bookId"));
		book.setBookId(bookId);

		if (book.getImage() == null) { // update (when image is changed)
			byte[] image = bookDAO.get(bookId).getImage();
			book.setImage(image);
		}

		String title = request.getParameter("title");
		Book bookExist = bookDAO.findByTitle(title);

		if (bookExist != null && bookExist.getBookId() != bookId) {
			message = "Cannot update book as Book [" + title + "] already exists.";
			CommonUtility.showMessageBackend(message, request, response);
		} else {

			book = bookDAO.update(book);
			message = "Book [" + title + "] updated successfully.";
			listBook(message);
		}

	}

	public void setBook(Book book) throws IOException, ServletException {

		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		String isbn = request.getParameter("isbn");
		float price = Float.parseFloat(request.getParameter("price"));

		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		Category category = categoryDAO.get(categoryId);

		Date publishDate = null;
		try {
			publishDate = DateUtils.parseDate(request.getParameter("publishDate"), new String[] { "yyyy-MM-dd" });
		} catch (Exception e) {
			e.printStackTrace();
		}

		byte[] image = null;
		Part part = request.getPart("image");
		if (part.getSize() != 0) { // create and update(when image is not changed)
			long size = part.getSize();
			image = new byte[(int) size];
			InputStream is = part.getInputStream();
			is.read(image);
		}

		book.setCategory(category);
		book.setTitle(title);
		book.setAuthor(author);
		book.setDescription(description);
		book.setIsbn(isbn);
		book.setImage(image);
		book.setPrice(price);
		book.setPublishDate(publishDate);
	}

	public void deleteBook() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(bookId);
		String message = null;

		if (book == null) {
			message = "Book with id [" + bookId + "] not found.";
			CommonUtility.showMessageBackend(message, request, response);
		} else {
			message = "Book with id [" + bookId + "] deleted successfully.";
			request.setAttribute("message", message);
			bookDAO.delete(bookId);
			listBook();
		}
	}

	public void listByCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		List<Book> bookList = bookDAO.findBookByCategory(categoryId);
		Category category = categoryDAO.get(categoryId);

		request.setAttribute("category", category);
		request.setAttribute("bookList", bookList);
		CommonUtility.forwardToPage("frontend/books_list_by_category.jsp", request, response);
	}

	public void listNewBooks() throws ServletException, IOException {
		List<Book> bookList = bookDAO.listNewBooks();
		request.setAttribute("newBookList", bookList);
		CommonUtility.forwardToPage("frontend/index.jsp", request, response);
	}

	public void viewBook() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(bookId);

		if (book == null) {
			String message = "Sorry, the book with ID [" + bookId + "] is not available.";
			CommonUtility.showMessageFrontend(message, request, response);
		} else {
			request.setAttribute("book", book);
			CommonUtility.forwardToPage("frontend/book_view.jsp", request, response);
		}
	}

	public void searchBook() throws ServletException, IOException {
		String searchText = request.getParameter("search").toLowerCase();
		List<Book> bookList = bookDAO.searchBooks(searchText);
		request.setAttribute("bookList", bookList);
		CommonUtility.forwardToPage("frontend/book_search.jsp", request, response);
	}

}
