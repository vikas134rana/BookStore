<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.bookstore.entity.Users" import="java.util.List"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Management</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">

		<div class='page_heading'>Customer Management</div>

		<h3>
			<a href="customer_form.jsp">Create New Customer</a>
		</h3>

		<c:if test="${requestScope.message!=null}">
			<h4>
				<i>${requestScope.message}</i>
			</h4>
		</c:if>
		<table class="list">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Email</th>
				<th>Full Name</th>
				<th>City</th>
				<th>Country</th>
				<th>Register On</th>
				<th>Actions</th>
			</tr>



			<c:forEach var="customer" items="${customerList}" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${customer.customerId}</td>
					<td>${customer.email}</td>
					<td>${customer.fullName}</td>
					<td>${customer.city}</td>
					<td>${customer.country}</td>
					<td>${customer.registerOn}</td>
					<td><a href="edit_customer?id=${customer.customerId}">Edit</a>&nbsp;&nbsp;<a
						href="#" onclick="confirmDelete(${customer.customerId})">Delete</a></td>
				</tr>
			</c:forEach>

		</table>

	</div>

	<jsp:directive.include file="footer.jsp" />

</body>

<script type="text/javascript">
	function confirmDelete(id) {
		if (confirm("Are you sure you want to delete Customer with Id " + id)) {
			window.location = "delete_customer?id="+id;
		}
	}
</script>

</html>