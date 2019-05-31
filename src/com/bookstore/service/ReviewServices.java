package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewServices {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private ReviewDAO reviewDAO;
	private BookDAO bookDAO;

	public ReviewServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.reviewDAO = new ReviewDAO();
		this.bookDAO = new BookDAO();
	}

	public void listAllReview() throws ServletException, IOException {
		listAllReview(null);
	}

	public void listAllReview(String message) throws ServletException, IOException {
		List<Review> reviewList = reviewDAO.listAll();
		request.setAttribute("reviewList", reviewList);
		if (message != null) {
			request.setAttribute("message", message);
		}
		CommonUtility.forwardToPage("review_list.jsp", request, response);
	}

	public void editReview() throws ServletException, IOException {
		int reviewId = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDAO.get(reviewId);
		request.setAttribute("review", review);
		CommonUtility.forwardToPage("review_form.jsp", request, response);
	}

	public void updateReview() throws ServletException, IOException {
		int reviewId = Integer.parseInt(request.getParameter("reviewId"));
		Review review = reviewDAO.get(reviewId);

		float rating = Float.parseFloat(request.getParameter("rating"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");

		review.setRating(rating);
		review.setHeadline(headline);
		review.setComment(comment);

		reviewDAO.update(review);

		String message = "Updated Review successfully with id [" + reviewId + "]";
		listAllReview(message);
	}

	public void deleteReview() throws ServletException, IOException {
		int reviewId = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDAO.get(reviewId);

		if (review == null) {
			String message = "Review with id [" + reviewId + "] not found";
			CommonUtility.showMessageBackend(message, request, response);
		} else {
			String message = "Review with id [" + reviewId + "] deleted successsfully";
			reviewDAO.delete(reviewId);
			listAllReview(message);
		}
	}

	public void showReviewForm() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(bookId);
		request.setAttribute("book", book);
		Customer customer = (Customer) request.getSession(false).getAttribute("customer");

		Review reviewExist = reviewDAO.findbyCustomerAndBook(customer.getCustomerId(), bookId);

		if (reviewExist != null) {
			request.setAttribute("review", reviewExist);
			CommonUtility.forwardToPage("frontend/review_read.jsp", request, response);
		} else {
			CommonUtility.forwardToPage("frontend/review_write.jsp", request, response);
		}
	}

	public void createReview() throws ServletException, IOException {

		Review review = new Review();

		int bookId = Integer.parseInt(request.getParameter("bookId"));

		Book book = bookDAO.get(bookId);
		Customer customer = (Customer) request.getSession(false).getAttribute("customer");

		for (Review rev : book.getReviews()) {

			if ((int) rev.getCustomer().getCustomerId() == (int) customer.getCustomerId()) {
				System.out.println("### Already reviewed by the customer");
				CommonUtility.showMessageFrontend("You can't add more than one comment on a same book.", request, response);
				return;
			}
		}

		float rating = Float.parseFloat(request.getParameter("rating"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");

		review.setBook(book);
		review.setCustomer(customer);
		review.setRating(rating);
		review.setHeadline(headline);
		review.setComment(comment);

		review = reviewDAO.create(review);
		System.out.println("### Created Review successfully");

		request.setAttribute("book", bookDAO.get(bookId));
		CommonUtility.forwardToPage("frontend/book_view.jsp", request, response);

	}

}
