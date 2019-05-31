package com.bookstore.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.ReviewDAO;

public class ReviewDaoTest {

	private static ReviewDAO reviewDAO;
	private static BookDAO bookDAO;
	private static CustomerDAO customerDAO;

	public ReviewDaoTest() {
	}

	@BeforeClass
	public static void setUp() {
		reviewDAO = new ReviewDAO();
		bookDAO = new BookDAO();
		customerDAO = new CustomerDAO();
	}

	@Test
	public void listAllReviewTest() {
		List<Review> reviewList = reviewDAO.listAll();
		assert (reviewList.size() > 0);
	}

	@Test
	public void countReviewTest() {
		long count = reviewDAO.count();
		assert (count > 0);
	}

	@Test
	public void createReviewTest() {

		Review review = new Review();

		review.setBook(bookDAO.get(3));
		review.setCustomer(customerDAO.get(4));
		review.setRating(4);
		review.setHeadline("Nice Book!");
		review.setComment("Explaned Concept easily");

		review = reviewDAO.create(review);

		assert (review.getReviewId() > 0);
	}

	@Test
	public void updateReviewTest() {
		Review review = reviewDAO.get(1);
		String newHeadline = "Love this book";
		review.setHeadline(newHeadline);
		review = reviewDAO.update(review);
		assertEquals(newHeadline, review.getHeadline());
	}

	@Test
	public void getReviewTest() {
		Review review = reviewDAO.get(1);
		assertNotNull(review);
	}

	@Test
	public void findByCustomerAndBookNotFound() {
		Review review = reviewDAO.findbyCustomerAndBook(99, 99);
		assertNull(review);
	}
	
	@Test
	public void findByCustomerAndBookFound() {
		Review review = reviewDAO.findbyCustomerAndBook(1,1);
		assertNotNull(review);
	}

	@AfterClass
	public static void tearUp() {
		reviewDAO.close();
	}

}
