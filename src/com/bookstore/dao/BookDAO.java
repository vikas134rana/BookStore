package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Book;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book> {

	public BookDAO(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Book create(Book book) {
		return super.create(book);
	}

	@Override
	public Book update(Book book) {
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

	public List<Book> findByTitle(String title) {
		return super.findWithNamedQuery("Book.findByTitle", "title", title);
	}

}
