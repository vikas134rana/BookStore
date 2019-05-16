<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Management</title>

<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/jquery-ui.css">
<link rel="stylesheet" href="../css/richtext.min.css">

<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/jquery.richtext.min.js"></script>

</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">

		<c:if test="${book==null}">
			<div class='page_heading'>Create Book</div>
			<form id="book_form" method="post" action="create_book"
				enctype="multipart/form-data">
		</c:if>

		<c:if test="${book!=null}">
			<div class='page_heading'>Edit Book</div>
			<form id="book_form" method="post" action="update_book"
				enctype="multipart/form-data">
				<input type="hidden" id="bookId" name="bookId"
					value="${book.bookId}">
				</td>
		</c:if>

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
				<td><textarea id="description" rows="6" cols="23"
						name="description">${book.description}</textarea>
			</tr>
			<tr>
				<td>Category:</td>
				<td><select name="categoryId" id="categoryId">
						<c:forEach var="cat" items="${categoryList}">

							<c:if test="${cat.categoryId eq book.category.categoryId}">
								<option value="${cat.categoryId}" selected="selected">${cat.name}</option>
							</c:if>
							<c:if test="${cat.categoryId ne book.category.categoryId}">
								<option value="${cat.categoryId}">${cat.name}</option>
							</c:if>

						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>ISBN:</td>
				<td><input id="isbn" name="isbn" value="${book.isbn}"></td>
			</tr>
			<tr>
				<td>Image:</td>
				<td><img alt="" id="thumbnail" name="thumbnail"
					style="width: 20%" src="data:image/png;base64,${book.imageBase64}"><br>
					<input type="file" id="image" name="image"></td>
			</tr>
			<tr>
				<td>Price:</td>
				<td><input id="price" name="price" value="${book.price}"></td>
			</tr>
			<tr>
				<td>Publish Date:</td>
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

		$('#description').richText();
		
		$("#publishDate").datepicker({
			dateFormat : 'yy-mm-dd'
		});

		$("#image").change(function() {
			readURL(this);
		});

		$("#book_form").validate({

			rules : {
				title : "required",
				author : "required",
				description : "required",
				categoryId : "required",
				isbn : "required",

				<c:if test="${book==null}">
					image : "required",
				</c:if>
				price : "required",
				publishDate : "required"
			}

		});
	});

	function readURL(input) {

		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#thumbnail').attr('src', e.target.result);
			}

			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
</html>