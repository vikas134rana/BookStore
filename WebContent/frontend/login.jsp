<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Login</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="../css/jquery-ui.css">

<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
</head>
<body>

	<div align="center">

		<h3>Login User</h3>
		<form method="post" action="login">

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
	$().ready(function() {

		$("form").validate({

			rules : {
				email : "required",
				password : "required",
			}
		});
	});
</script>
</html>