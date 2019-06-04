<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.bookstore.entity.Users" import="java.util.List"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${book.title}-Book</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">

		<div class="book_detail_container">
			<div class="book_detail_img_container">
				<img alt="" src="data:image/png;base64,${book.imageBase64}"
					class=".book_detail_img">
			</div>
			<div class="book_detail_info_container">

				<div class="book_detail_title">
					<b>${book.title}</b>
				</div>

				<div>
					<i>by ${book.author}</i>
				</div>

				<div class="book_detail_price">
					<b>$${book.price}</b>
				</div>

				<div class="book_detail_description">
					<div class="book_detail_description_head">
						<b>Description</b>
					</div>
					<p>${book.description}</p>
				</div>

				<div class="book_detail_Rating">
					<div id="star_rating_book${book.bookId}">
						<jsp:include page="star_rating.jsp">
							<jsp:param name="ratingContainerId"
								value="star_rating_book${book.bookId}" />
							<jsp:param name="rating" value="${book.ratingAvg}" />
						</jsp:include>
					</div> 
				</div>

				<div class="book_detail_button_container">
					<a class="book_detail_buy_button">Buy now</a> <a
						class="book_detail_cart_button" href="add_cart?id=${book.bookId}">Add to cart</a>
				</div>

			</div>

			<div style="border-top: 1px solid gray;"></div>

			<div style="padding: 20px;" align="left">

				<div>
					<h3>
						<a href="write_review?id=${book.bookId}"
							style="font-size: 20px; padding: 8px; color: white; background: #3EADA6; border-radius: 5px; margin: 10px;">
							Create a review </a>
					</h3>
				</div>

				<c:if test="${requestScope.message!=null}">
					<h4>
						<i>${requestScope.message}</i>
					</h4>
				</c:if>

				<h3>Reviews (${book.reviews.size()})</h3>

				<c:forEach var="review" items="${book.reviews}">

					<div class="review_container"
						style="width: 700px; border: 1px solid gray; border-radius: 10px; padding: 10px; margin-top: 20px;">

						<div>
							<span id="star_rating_customer${review.reviewId}"
								style="width: 50px;"> <jsp:include page="star_rating.jsp">
									<jsp:param name="ratingContainerId"
										value="star_rating_customer${review.reviewId}" />
									<jsp:param name="rating" value="${review.rating}" />
								</jsp:include>
							</span> <span style="font-size: 20px;"> <b>${review.headline}</b>
							</span>
						</div>

						<div>
							<span>by <i>${review.customer.fullName}</i> on <%-- ${review.reviewOn} --%>
								<c:set var="reviewOn"
									value="${fn:substring(review.reviewOn, 0, 10)}" /> ${reviewOn}
							</span>
						</div>

						<div style="padding: 5px;">
							<span>${review.comment}</span>
						</div>

					</div>

				</c:forEach>

			</div>

		</div>



	</div>

	<jsp:directive.include file="footer.jsp" />
</body>

<script type="text/javascript">
	function confirmDelete(id) {
		if (confirm("Are you sure you want to delete Book with bookId " + id)) {
			window.location = "delete_book?id=" + id;
		}
	}
</script>

</html>