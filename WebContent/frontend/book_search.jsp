<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.bookstore.entity.Users" import="java.util.List"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Management</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<jsp:directive.include file="header.jsp" />


	<div align="center">

		<c:if test="${bookList.size()==0}">
			<h2>No Item Found</h2>
		</c:if>


		<c:forEach var="book" items="${bookList}" varStatus="status">
			<div class="book_search_container">
				<div class="book_search_img_container">
					<a href="view_book?id=${book.bookId}"><img alt=""
						src="data:image/png;base64,${book.imageBase64}"
						class="book_search_img" style="width: 150px; height: 195px"></a>
				</div>
				<div class="book_search_info_container">

					<div class="book_search_title">
						<a href="view_book?id=${book.bookId}"><b>${book.title}</b></a>
					</div>

					<div>
						<i>by ${book.author}</i>
					</div>

					<div class="book_search_description_container">
						<div class="book_search_description">
							<b>Description</b>
						</div>
						<div>${book.description}</div>
					</div>

					<div class="book_search_rating">
						<b>Rating *****</b>
					</div>
				</div>
				<div class="book_search_buy_container">
					<div class="book_search_price">
						<b>$${book.price}</b>
					</div>

					<a class="book_search_buy">Buy now</a> <a class="book_search_cart">Add
						to cart</a>
				</div>

			</div>
		</c:forEach>
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