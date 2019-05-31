<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Category Management</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">

		<c:if test="${category==null}">
			<div class='page_heading'>Create Category</div>
			<form id="category_form" method="post" action="create_category"
				onsubmit="return validateFormField();">
		</c:if>

		<c:if test="${category!=null}">
			<div class='page_heading'>Edit Category</div>
			<form id="category_form" method="post" action="update_category"
				onsubmit="return validateFormField();">
		</c:if>

		<input type="hidden" id="categoryId" name="categoryId"
			value="${category.categoryId}">
		<table class="create">
			<tr>
				<td>CategoryName:</td>
				<td><input type="text" id="categoryName" name="categoryName"
					value="${category.name}"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input id="submit" type="button"
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
		$("#category_form").validate({

			rules : {
				categoryName : "required"
			},

			messages : {
				categoryName : "Please enter Category Name"
			}

		});
	});

	/*- function validateFormField() {

		var username = document.getElementById("username");
		if (username.value.length == 0) {
			alert('Username is required');
			return false;
		}

		var email = document.getElementById("email");
		if (email.value.length == 0) {
			alert('Email is required');
			return false;
		}

		var password = document.getElementById("password");
		if (password.value.length == 0) {
			alert('Password is required');
			return false;
		}

		return true;
	} */
</script>
</html>