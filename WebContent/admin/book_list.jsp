<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.bookstore.entity.Users" import="java.util.List"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Management</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">

		<div class='page_heading'>Book Management</div>

		<h3>
			<a href="new_book">Create New Book</a>
		</h3>

		<c:if test="${requestScope.message!=null}">
			<h4>
				<i>${requestScope.message}</i>
			</h4>
		</c:if>
		<table class="list">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Image</th>
				<th>Title</th>
				<th>Author</th>
				<th>Category</th>
				<th>Publish Date</th>
				<th>ISBN</th>
				<th>Price</th>
				<th>Actions</th>
			</tr>



			<c:forEach var="book" items="${bookList}" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${book.bookId}</td>
					<td><img alt=""
						src="data:image/png;base64,${book.imageBase64}" width="100px"
						height="130px"></td>
					<td>${book.title}</td>
					<td>${book.author}</td>
					<td>${book.category.name}</td>
					<td>${book.publishDate}</td>
					<td>${book.isbn}</td>
					<td>${book.price}</td>
					<td><a href="edit_book?id=${book.bookId}">Edit</a>&nbsp;&nbsp;
						<a href="#" onclick="confirmDelete(${book.bookId})">Delete</a></td>
				</tr>
			</c:forEach>

		</table>

	</div>

	<jsp:directive.include file="footer.jsp" />

</body>

<script type="text/javascript">
	function confirmDelete(id) {
		if (confirm("Are you sure you want to delete Book with bookId " + id)) {
			window.location = "delete_book?id="+id;
		}
	}
</script>

</html>