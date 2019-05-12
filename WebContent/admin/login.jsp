<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>

	<div align="center">

		<h3>Login User</h3>
		<form method="post" action="login"
			onsubmit="return validateFormField();">

			<c:if test="${message!=null}">
				<h4>${message}</h4>
			</c:if>


			<table>
				<tr>
					<td>Email:</td>
					<td><input type="email" id="email" name="email"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" id="password" name="password"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Login"></td>
				</tr>
			</table>

		</form>

	</div>

	<jsp:directive.include file="footer.jsp" />

</body>
<script>
	function validateFormField() {

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
	}
</script>
</html>