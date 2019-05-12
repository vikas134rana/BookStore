package com.bookstore.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;

	public BaseServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStore");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public void destroy() {
		entityManager.close();
		entityManagerFactory.close();
	}
}
