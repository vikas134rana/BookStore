package com.bookstore.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.bookstore.entity.Book;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book> {

	public BookDAO() {
		super();
	}

	@Override
	public Book create(Book book) {
		book.setLastUpdatedOn(new Date());
		return super.create(book);
	}

	@Override
	public Book update(Book book) {
		book.setLastUpdatedOn(new Date());
		return super.update(book);
	}

	@Override
	public Book get(Object bookId) {
		return super.find(Book.class, bookId);
	}

	@Override
	public void delete(Object bookId) {
		super.delete(Book.class, bookId);
	}

	@Override
	public List<Book> listAll() {
		return super.findWithNamedQuery("Book.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Book.countAll");
	}

	public Book findByTitle(String title) {
		List<Book> bookList = super.findWithNamedQuery("Book.findByTitle", "title", title);

		if (bookList.size() == 1)
			return bookList.get(0);

		return null;
	}

	public List<Book> findBookByCategory(int categoryId) {
		return super.findWithNamedQuery("Book.findByCategory", "category_id", String.valueOf(categoryId));
	}

	public long countBookByCategory(int categoryId) {
		return super.countWithNamedQuery("Book.countByCategory", "category_id", String.valueOf(categoryId));
	}

	public List<Book> listNewBooks() {
		return super.findWithNamedQuery("Book.listNewBooks", 0, 4);
	}

	public List<Book> searchBooks(String searchText) {
		searchText = "%" + searchText.toLowerCase() + "%";
		return super.findWithNamedQuery("Book.search", "searchText", searchText);
	}

}
