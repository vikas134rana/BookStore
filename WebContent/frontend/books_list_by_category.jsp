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
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">

		<div class='page_heading'>${category.name}</div>

		<c:forEach var="book" items="${bookList}" varStatus="status">
			<div style="display: inline-block; border: 1px solid gray; border-radius: 10px; padding: 15px; margin-right: 20px;">
				<div>
					<a href="view_book?id=${book.bookId}"><img alt=""
						src="data:image/png;base64,${book.imageBase64}"
						style="width: 150px; height: 200px"></a>
				</div>
				<div style="font-size: 18px;">
					<a href="view_book?id=${book.bookId}"><b>${book.title}</b></a>
				</div>
				<div>
					<i>by ${book.author}</i>
				</div>
				<div>
					<b>$${book.price}</b>
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