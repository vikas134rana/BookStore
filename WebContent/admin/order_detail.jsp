<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.bookstore.entity.Users" import="java.util.List"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Detail</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">

		<div class='page_heading'>Detail of Order : ${order.orderId}</div>

		<h2>Order Overview</h2>

		<c:if test="${requestScope.message!=null}">
			<h4>
				<i>${requestScope.message}</i>
			</h4>
		</c:if>

		<table class="view">
			<tr>
				<td>Order ID</td>
				<td>${order.orderId}</td>
			</tr>

			<tr>
				<td>Ordered By</td>
				<td>${order.customer.fullName}</td>
			</tr>

			<tr>
				<td>Quantity</td>
				<td>${order.getTotalQuantity()}</td>
			</tr>

			<tr>
				<td>Total</td>
				<td>${order.total}</td>
			</tr>

			<tr>
				<td>Payment Method</td>
				<td>${order.paymentMethod}</td>
			</tr>

			<tr>
				<td>Status</td>
				<td>${order.status}</td>
			</tr>

			<tr>
				<td>Order Date</td>
				<td>${order.orderDate}</td>
			</tr>
		</table>


		<h2>Ordered Books</h2>

		<table class="list">

			<tr>
				<th>Index</th>
				<th>Book Title</th>
				<th>Author</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Sub Total</th>
			</tr>

			<c:forEach var="orderDetail" items="${order.orderDetails}"
				varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${orderDetail.book.title}</td>
					<td>${orderDetail.book.author}</td>
					<td>${orderDetail.book.price}</td>
					<td>${orderDetail.quantity}</td>
					<td>$${orderDetail.subtotal}</td>
				</tr>
			</c:forEach>

			<tr>
				<td colspan="3"></td>
				<td ><b>TOTAL</b></td>
				<td>${order.getTotalQuantity()}</td>
				<td>$${order.total}</td>
			</tr>

		</table>

	</div>

	<jsp:directive.include file="footer.jsp" />

</body>


</html>