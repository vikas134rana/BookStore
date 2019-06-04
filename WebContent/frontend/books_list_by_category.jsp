<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.bookstore.entity.Users" import="java.util.List"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${category.name}Book</title>
<link rel="stylesheet" href="css/style.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">

		<div class='page_heading'>${category.name}</div>

		<c:forEach var="book" items="${bookList}" varStatus="status">
			<div class="book_list_container">
				<div>
					<a href="view_book?id=${book.bookId}"><img
						class="book_list_img" alt=""
						src="data:image/png;base64,${book.imageBase64}"></a>
				</div>
				<div class="book_list_title">
					<a href="view_book?id=${book.bookId}"><b>${book.title}</b></a>
				</div>
				<div>
					<i>by ${book.author}</i>
				</div>
				<div>
					<b>$${book.price}</b>
				</div>
				<div id="star_rating_book${book.bookId}">
					<jsp:include page="star_rating.jsp">
						<jsp:param name="ratingContainerId"
							value="star_rating_book${book.bookId}" />
						<jsp:param name="rating" value="${book.ratingAvg}" />
					</jsp:include>(${book.reviews.size()})
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