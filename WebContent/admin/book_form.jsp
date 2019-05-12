<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Management</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">

		<c:if test="${user==null}">
			<div class='page_heading'>Create Book</div>
			<form id="book_form" method="post" action="create_book">
		</c:if>

		<c:if test="${user!=null}">
			<div class='page_heading'>Edit Book</div>
			<form id="book_form" method="post" action="update_book">
		</c:if>

		<input type="hidden" id="bookId" name="bookId" value="${book.bookId}">
		<table>
			<tr>
				<td>Title:</td>
				<td><input type="text" id="title" name="title"
					value="${book.title}"></td>
			</tr>
			<tr>
				<td>Author:</td>
				<td><input id="author" name="author" value="${book.author}"></td>
			</tr>
			<tr>
				<td>Description:</td>
				<td><input id="description" name="description"
					value="${book.description}"></td>
			</tr>
			<tr>
				<td>Category:</td>
				<td>
					<select name="categoryId" id="categoryId">
						<c:forEach var="cat" items="${categoryList}">
							<option value="cat.categoryId" >${cat.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>ISBN:</td>
				<td><input id="isbn" name="isbn" value="${book.isbn}"></td>
			</tr>
			<tr>
				<td>Image:</td>
				<td><input type="file" id="image" name="image"
					value="${book.image}"></td>
			</tr>
			<tr>
				<td>Price:</td>
				<td><input id="price" name="price" value="${book.price}"></td>
			</tr>
			<tr>
				<td>PublishDate:</td>
				<td><input id="publishDate" name="publishDate"
					value="${book.publishDate}"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button"
					value="Cancel" onclick="javascript:history.go(-1);"> <input
					type="submit" value="Submit"></td>
			</tr>
		</table>

		</form>

	</div>

	<jsp:directive.include file="footer.jsp" />

</body>
<script>
	$().ready(function() {
		$("#user_form").validate({

			rules : {
				title : "required",
				author : "required",
				description : "required",
				isbn : "required",
				image : "required",
				price : "required",
				publishDate : "required"
			}

		});
	});
</script>
</html>