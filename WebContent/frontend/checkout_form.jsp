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
<title>Checkout Page</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">

		<h2>
			Review Your Order Details <a href="view_cart">Edit</a>
		</h2>


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
			<table class="list">
				<tr>
					<th>No</th>
					<th>Book</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Sub Total</th>
				</tr>

				<c:forEach var="entry" items="${cart.getItems()}" varStatus="status">
					<input type="hidden" name="bookId" value="${entry.key.bookId}">
					<tr>
						<c:set var="subTotalPrice">
							<fmt:formatNumber type="number" minFractionDigits="2"
								maxFractionDigits="2" value="${entry.value*entry.key.price}" />
						</c:set>

						<td>${status.index+1}</td>
						<td valign="middle"><img alt=""
							src="data:image/png;base64,${entry.key.imageBase64}" width="80px">
							<div>
								<b>${entry.key.title}</b>
							</div></td>
						<td><input readonly="readonly" size="5"
							id="quantity${status.index+1}" name="quantity${status.index+1}"
							value="${entry.value}"></td>
						<td>$${entry.key.price}</td>
						<td>$${subTotalPrice}</td>
					</tr>
				</c:forEach>

				<tr>
					<td colspan="2"></td>
					<td>${cart.getTotalQuantity()}Copies</td>
					<td><b>TOTAL:</b></td>
					<td style="font-weight: bold; font-size: 20px;">$
						${cart.getTotalAmount()}</td>
				</tr>
			</table>

		</c:if>

		<h2>Your Shipping Information</h2>

		<form id="shipping_info" action="place_order" method="post">
			<table class="create">

				<tr>
					<td>Recipient Name</td>
					<td><input name="recipient_name" id="recipient_name"
						value="${customer.fullName}"></td>
				</tr>

				<tr>
					<td>Recipient Phone</td>
					<td><input name="recipient_phone" id="recipient_phone"
						value="${customer.phone}"></td>
				</tr>

				<tr>
					<td>Street Address</td>
					<td><textarea name="address" id="address" rows="7" cols="23">${customer.address}</textarea></td>
				</tr>

				<tr>
					<td>City</td>
					<td><input name="city" id="city" value="${customer.city}"></td>
				</tr>

				<tr>
					<td>Zipcode</td>
					<td><input name="zipcode" id="zipcode"
						value="${customer.zipcode}"></td>
				</tr>

				<tr>
					<td>Country</td>
					<td><input name="country" id="country"
						value="${customer.country}"></td>
				</tr>

			</table>

			<h3>Payment</h3>

			<span><b>Choose your Payment method:</b></span> <select
				name="payment_method" id="payment_method">
				<option value="Cash On Delivery">Cash On Delivery</option>
			</select>

			<div>
				<input type="submit" value="Place Order"> <a
					class="continue_shopping" href="/">Continue Shopping</a>
			</div>

		</form>

	</div>

	<jsp:directive.include file="footer.jsp" />

</body>

<script type="text/javascript">
	$(document).ready(function() {

		$("#shipping_info").validate({
			rules : {
				recipient_name : "required",
				recipient_phone : "required",
				address : "required",
				city : "required",
				Zipcode : "required",
				country : "required",
			}
		});

	});
</script>
</html>