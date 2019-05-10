<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">

		<c:if test="${user==null}">
			<h3>Create User</h3>
			<form method="post" action="create_user" onsubmit="return validateFormField(true);">
		</c:if>

		<c:if test="${user!=null}">
			<h3>Edit User</h3>
			<form method="post" action="update_user" onsubmit="return validateFormField(false);">
		</c:if>

			<input type="hidden" id="userId" name="userId"
				value="${user.userId}">
			<table>
				<tr>
					<td>UserName:</td>
					<td><input type="text" id="username" name="username"
						value="${user.fullName}"></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input type="email" id="email" name="email"
						value="${user.email}"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" id="password" name="password"
						value=""></td>
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
	function validateFormField(requiredPassword) {

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
		if (requiredPassword && password.value.length == 0) {
			alert('Password is required');
			return false;
		}

		return true;
	}
</script>
</html>