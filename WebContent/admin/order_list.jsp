<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.bookstore.entity.Users" import="java.util.List"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Management</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">

		<div class='page_heading'>Order Management</div>


		<c:if test="${requestScope.message!=null}">
			<h4>
				<i>${requestScope.message}</i>
			</h4>
		</c:if>
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
				<th>Actions</th>
			</tr>



			<c:forEach var="order" items="${orderList}" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${order.orderId}</td>
					<td>${order.customer.fullName}</td>
					<td>${order.getTotalQuantity()}</td>
					<td>$${order.total}</td>
					<td>${order.paymentMethod}</td>
					<td>${order.status}</td>
					<td>${order.orderDate}</td>
					<td><a href="detail_order?id=${order.orderId}">Detail</a>&nbsp;&nbsp;&nbsp;<a
						href="edit_category?id=${order.orderId}">Edit</a>&nbsp;&nbsp;&nbsp;<a
						href="#" onclick="confirmDelete(${order.orderId})">Delete</a></td>
				</tr>
			</c:forEach>

		</table>

	</div>

	<jsp:directive.include file="footer.jsp" />

</body>

<script type="text/javascript">
	function confirmDelete(id) {
		if (confirm("Are you sure you want to delete Order with Id " + id)) {
			window.location = "delete_order?id="+id;
		}
	}
</script>

</html>