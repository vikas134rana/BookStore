<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.bookstore.entity.Users"
	import="java.util.List,com.bookstore.controller.frontend.cart.Cart "%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">

		<div class='page_heading'>Your Cart Details</div>


		<c:if test="${requestScope.message!=null}">
			<h4>
				<i>${requestScope.message}</i>
			</h4>
		</c:if>

		<c:if test="${cart==null || cart.isEmpty()}">
			<h2>
				<i>Cart Is Empty.</i>
			</h2>
		</c:if>

		<c:if test="${cart!=null && !cart.isEmpty()}">
			<form id="cart_form" action="update_cart" method="post">
				<table class="list">
					<tr>
						<th>No</th>
						<th>Book</th>
						<th>Quantity</th>
						<th>Price</th>
						<th>Sub Total</th>
						<th><a href="clear_cart">Clear Cart</a></th>
					</tr>

					<c:forEach var="entry" items="${cart.getItems()}"
						varStatus="status">
						<input type="hidden" name="bookId" value="${entry.key.bookId}">
						<tr>
							<c:set var="subTotalPrice">
								<fmt:formatNumber type="number" minFractionDigits="2"
									maxFractionDigits="2" value="${entry.value*entry.key.price}" />
							</c:set>

							<td>${status.index+1}</td>
							<td valign="middle"><img alt=""
								src="data:image/png;base64,${entry.key.imageBase64}"
								width="80px">
								<div>
									<b>${entry.key.title}</b>
								</div></td>
							<td><input size="5" id="quantity${status.index+1}"
								name="quantity${status.index+1}" value="${entry.value}"></td>
							<td>$${entry.key.price}</td>
							<td>$${subTotalPrice}</td>
							<td><a href="remove_cart?id=${entry.key.bookId}">Remove</a></td>
						</tr>
					</c:forEach>

					<tr>
						<td colspan="2"></td>
						<td>${cart.getTotalQuantity()}Copies</td>
						<td><b>TOTAL:</b></td>
						<td style="font-weight: bold; font-size: 20px;">$
							${cart.getTotalAmount()}</td>
						<td></td>
					</tr>
				</table>

				<br> <input type="submit" value="Update"> <a
					href="checkout">Checkout</a>
			</form>
		</c:if>

	</div>

	<jsp:directive.include file="footer.jsp" />

</body>

<script type="text/javascript">

$(document).ready(function() {
	
	$("#cart_form").validate({
		rules : {
			<c:forEach items="${cart.items}" var="item" varStatus="status">
				quantity${status.index + 1}: {
					required: true, number: true, min: 1
				},
			</c:forEach>
		},

		messages : {
			<c:forEach items="${cart.items}" var="item" varStatus="status">
				quantity${status.index + 1}: { 
					required: "Please enter quantity",
					number: "Quantity must be a number",
					min: "Quantity must be greater than 0"
				},
			</c:forEach>					
		}
	});

});

</script>
</html>