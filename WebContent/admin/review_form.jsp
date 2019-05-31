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

		<div class='page_heading'>Edit Book</div>
		<form id="review_form" method="post" action="update_review">
			<input type="hidden" id="reviewId" name="reviewId"
				value="${review.reviewId}">

			
			<table class="create">
				<tr>
					<td>Book:</td>
					<td>${review.book.title}</td>
				</tr>
				<tr>
					<td>Customer:</td>
					<td>${review.customer.fullName}</td>
				</tr>
				<tr>
					<td>Rating:</td>
					<td>${review.rating}</td>

				</tr>
				<tr>
					<td>Headline</td>
					<td><input id="headline" name="headline"
						value="${review.headline}"></td>
				</tr>
				<tr>
					<td>Comment:</td>
					<td><textarea id="comment" name="comment">${review.comment}</textarea></td>
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

		$('#comment').richText();

		$("#book_form").validate({

			rules : {
				rating : "required",
				headline : "required",
				comment : "required",
			}

		});
	});
</script>
</html>