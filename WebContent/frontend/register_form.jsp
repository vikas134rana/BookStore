<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>

<link rel="stylesheet" href="css/style.css">

<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

</head>
<body>

	<jsp:directive.include file="header.jsp" />



	<div align="center">

		<div class='page_heading'>Create Customer</div>
		<form id="customer_form" method="post" action="register_customer">

			<table>
				<tr>
					<td>Email:</td>
					<td><input type="text" id="email" name="email"></td>
				</tr>
				<tr>
					<td>Full Name:</td>
					<td><input id="fullName" name="fullName"></td>
				</tr>
				<tr>
					<td>Address:</td>
					<td><textarea id="address" rows="6" cols="23" name="address"></textarea>
				</tr>
				<tr>
					<td>City:</td>
					<td><input id="city" name="city"></td>
				</tr>
				<tr>
					<td>Country:</td>
					<td><input id="country" name="country"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input id="password" name="password"></td>
				</tr>
				<tr>
					<td>Phone:</td>
					<td><input id="phone" name="phone"></td>
				</tr>
				<tr>
					<td>Zipcode:</td>
					<td><input id="zipcode" name="zipcode"></td>
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

		$("#customer_form").validate({

			rules : {
				email : "required",
				fullName : "required",
				address : "required",
				city : "required",
				country : "required",
				password : "required",
				phone : "required",
				zipcode : "required"
			}

		});
	});
</script>
</html>