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
			<div
				style="border: 1px solid gray; background: white; border-radius: 10px; padding: 15px; margin-right: 20px; margin-top: 20px; width: 70%;">
				<div
					style="display: inline-block; padding-right: 30px; border-right: 1px solid #efeded;">
					<a href="view_book?id=${book.bookId}"><img alt=""
						src="data:image/png;base64,${book.imageBase64}"
						style="width: 150px; height: 195px"></a>
				</div>
				<div
					style="display: inline-block; vertical-align: top; margin-left: 30px; border-right: 1px solid #efeded;">

					<div style="font-size: 24px; padding: 5px;">
						<a href="view_book?id=${book.bookId}"><b>${book.title}</b></a>
					</div>

					<div>
						<i>by ${book.author}</i>
					</div>

					<div style="padding: 5px; width: 500px; text-align: left">
						<div style="font-size: 18px;">
							<b>Description</b>
						</div>
						<div>${book.description}</div>
					</div>

					<div style="padding: 10px; font-size: 20px;">
						<b>Rating *****</b>
					</div>
				</div>
				<div
					style="display: inline-block; padding: 5px; vertical-align: bottom; margin: 30px; vertical-align: top;">
					<div style="font-size: 24px; color: #ffc107; padding: 15px; margin: 10px;">
						<b>$${book.price}</b>
					</div>

					<a
						style="font-size: 20px; padding: 8px; color: white; background: #007bff; border-radius: 5px; margin: 20px;">Buy
						now</a> <a
						style="font-size: 20px; padding: 8px; border: 1px solid #007bff; color: #007bff; background: white; border-radius: 5px; margin: 10px;">Add
						to cart</a>
				</div>

			</div>
		</c:forEach>
	</div>

	<%-- <div align="center">

		<h2>Found Books</h2>

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
			</tr>

			<c:if test="${bookList.size()==0}">
				<h2>No Item Found</h2>
			</c:if>

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
				</tr>
			</c:forEach>

		</table>

	</div> --%>
	<jsp:directive.include file="footer.jsp" />

</body>

<script type="text/javascript">
	function confirmDelete(id) {
		if (confirm("
						Are you sure you want to delete Book with
						bookId " + id)) {
			window.location="delete_book?id="+ id;
		}
	}
</script>
</html>