<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Management</title>

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

		<c:if test="${customer==null}">
			<div class='page_heading'>Create Customer</div>
			<form id="customer_form" method="post" action="create_customer">
		</c:if>

		<c:if test="${customer!=null}">
			<div class='page_heading'>Edit Customer</div>
			<form id="customer_form" method="post" action="update_customer">
				<input type="hidden" id="customerId" name="customerId"
					value="${customer.customerId}">
				</td>
		</c:if>

		<table>
			<tr>
				<td>Email:</td>
				<td><input type="text" id="email" name="email"
					value="${customer.email}"></td>
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
				<td>Password:</td>
				<td><input id="password" name="password"
					value=""></td>
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

				<c:if test="${customer==null}">
					password : "required",
				</c:if>
				phone : "required",
				zipcode : "required"
			}

		});
	});

</script>
</html>