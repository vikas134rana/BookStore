<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Profile</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2>Customer Profile</h2>
		<h3>Welcome, ${customer.fullName}</h3>

		<table id="customer_detail">
			<tr>
				<td><b>Email:</b></td>
				<td>${customer.email}</td>
			</tr>
			<tr>
				<td><b>Full Name:</b></td>
				<td>${customer.fullName}</td>
			</tr>
			<tr>
				<td><b>Phone Number:</b></td>
				<td>${customer.phone}</td>
			</tr>
			<tr>
				<td><b>Address:</b></td>
				<td>${customer.address}</td>
			</tr>
			<tr>
				<td><b>City:</b></td>
				<td>${customer.city}</td>
			</tr>
			<tr>
				<td><b>Zipcode:</b></td>
				<td>${customer.zipcode}</td>
			</tr>
			<tr>
				<td><b>Country:</b></td>
				<td>${customer.country}</td>
			</tr>

		</table>

		<h3>
			<a href="edit_profile">Edit Profile</a>
		</h3>

	</div>

	<jsp:directive.include file="footer.jsp" />

</body>
</html>