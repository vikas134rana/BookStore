<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Customer Profile</title>

<link rel="stylesheet" href="css/style.css">

<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

</head>
<body>

	<jsp:directive.include file="header.jsp" />



	<div align="center">

		<div class='page_heading'>Edit Customer Profile</div>
		<form id="edit_profile_form" method="post" action="update_profile">

			<table>
				<tr>
					<td>Email:</td>
					<td>${customer.email}</td>
				</tr>
				<tr>
					<td>Full Name:</td>
					<td><input id="fullName" name="fullName"
						value="${customer.fullName}"></td>
				</tr>
				<tr>
					<td>Address:</td>
					<td><textarea id="address" rows="6" cols="23" name="address">${customer.address}</textarea>
				</tr>
				<tr>
					<td>City:</td>
					<td><input id="city" name="city" value="${customer.city}"></td>
				</tr>
				<tr>
					<td>Country:</td>
					<td><input id="country" name="country"
						value="${customer.country}"></td>
				</tr>
				<tr>
					<td>Phone:</td>
					<td><input id="phone" name="phone" value="${customer.phone}"></td>
				</tr>
				<tr>
					<td>Zipcode:</td>
					<td><input id="zipcode" name="zipcode"
						value="${customer.zipcode}"></td>
				</tr>
				<tr>
					<td></td>
					<td><i>Leave Password field blank if you don't want to
							change password</i></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input id="password" name="password"></td>
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

		$("#edit_profile_form").validate({

			rules : {
				fullName : "required",
				address : "required",
				city : "required",
				country : "required",
				phone : "required",
				zipcode : "required"
			}

		});
	});
</script>
</html>