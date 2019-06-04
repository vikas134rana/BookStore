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

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<jsp:directive.include file="header.jsp" />

	<div align="center">

		<h2>Administrative Dashboard</h2>

		<hr width="70%"></hr>
		<div>
			<h3>Quick Actions:</h3>

			<a href="new_book" class="quickaction_item">New Book</a> <a
				href="user_form.jsp" class="quickaction_item">New User</a> <a
				href="category_form.jsp" class="quickaction_item">New Category</a> <a
				href="customer_form.jsp" class="quickaction_item">New Customer</a>
		</div>
		<hr width="70%"></hr>



		<h2>Recent Sales</h2>
		<table class="list">
			<tr>
				<th>Index</th>
				<th>Order ID</th>
				<th>Ordered By</th>
				<th>Quantity</th>
				<th>Total</th>
				<th>Payment Method</th>
				<th>Status</th>
				<th>Order Date</th>
			</tr>



			<c:forEach var="order" items="${recentSales}" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${order.orderId}</td>
					<td>${order.customer.fullName}</td>
					<td>${order.getTotalQuantity()}</td>
					<td>$${order.total}</td>
					<td>${order.paymentMethod}</td>
					<td>${order.status}</td>
					<td>${order.orderDate}</td>
				</tr>
			</c:forEach>

		</table>




		<h2>Recent Reviews</h2>
		<table class="list">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Book</th>
				<th>Rating</th>
				<th>Headline</th>
				<th>Customer</th>
				<th>Review On</th>
			</tr>



			<c:forEach var="review" items="${recentReviews}" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${review.reviewId}</td>
					<td>${review.book.title}</td>
					<td>${review.rating}</td>
					<td>${review.headline}</td>
					<td>${review.customer.fullName}</td>
					<td>${review.reviewOn}</td>
				</tr>
			</c:forEach>

		</table>


		<h2>Statistics</h2>
		<table class="list">
			<tr>
				<td><span><b>Total Users:</b> ${userCount}</span></td>
				<td><span><b>Total Books: </b> ${bookCount}</span></td>
				<td><span><b>Total Customers: </b>${customerCount}</span></td>
				<td><span><b>Total Reviews: </b>${reviewCount}</span></td>
				<td><span><b>Total Orders: </b>${orderCount}</span></td>
			</tr>
		</table>
	</div>

	<jsp:directive.include file="footer.jsp" />




</body>
</html>