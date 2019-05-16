<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					<b>***** Rating</b>
				</div>

				<div class="book_detail_button_container">
					<a class="book_detail_buy_button">Buy now</a> <a
						class="book_detail_cart_button">Add to cart</a>
				</div>

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