<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Home Page</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">

		<h2>Administrative Dashboard</h2>

		<hr width="35%"></hr>

		<div>
			<h3>Quick Actions:</h3>

			<a href="#" class="quickaction_item">New Book</a><a href="new_user"
				class="quickaction_item">New User</a> <a href="#"
				class="quickaction_item">New Category</a><a href="#"
				class="quickaction_item">New Customer</a>
		</div>
		<hr width="35%"></hr>



		<h2>Recent Sales</h2>
		<h2>Recent Reviews</h2>
		<h2>Statistics</h2>

	</div>

	<jsp:directive.include file="footer.jsp" />




</body>
</html>