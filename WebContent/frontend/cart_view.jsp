<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.bookstore.entity.Users" import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
<link rel="stylesheet" href="css/style.css">
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

		<c:if test="${cartMap==null || empty cartMap}">
			<h2>
				<i>Cart Is Empty.</i>
			</h2>
		</c:if>

		<c:if test="${cartMap!=null && not empty cartMap}">
			<table class="list">
				<tr>
					<th>No</th>
					<th>Book</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Sub Total</th>
					<th><a href="clear_cart">Clear Cart</a></th>
				</tr>

				<c:set var="totalQuantity" value="${0}" />
				<c:set var="totalPrice" value="${0}" />

				<c:forEach var="entry" items="${cartMap}" varStatus="status">
					<tr>

						<c:set var="totalQuantity"
							value="${totalQuantity + entry.value.quantity}" />
						<c:set var="subTotalPrice">
							<fmt:formatNumber type="number" minFractionDigits="2"
								maxFractionDigits="2"
								value="${entry.value.quantity*entry.value.price}" />
						</c:set>
						<c:set var="totalPrice" value="${totalPrice + subTotalPrice}" />

						<td>${status.index+1}</td>
						<td valign="middle"><img alt=""
							src="data:image/png;base64,${entry.value.imageBase64}"
							width="80px">
							<div>
								<b>${entry.value.bookName}</b>
							</div></td>
						<td>${entry.value.quantity}</td>
						<td>${entry.value.price}</td>
						<td>${subTotalPrice}</td>
						<td><a href="remove_cart?id=${entry.value.bookId}">Remove</a></td>
					</tr>
				</c:forEach>

				<tr>
					<td colspan="2"></td>
					<td>${totalQuantity} Copies</td>
					<td><b>TOTAL:</b></td>
					<td style="font-weight: bold; font-size: 20px;">$
						${totalPrice}</td>
					<td></td>
				</tr>
			</table>
		</c:if>

	</div>

	<jsp:directive.include file="footer.jsp" />

</body>

<script type="text/javascript">
	function confirmDelete(id) {
		if (confirm("
						Are you sure you want to delete Customer with
						Id " + id)) {
			window.location="delete_customer?id="+id;
		}
	}
</script>
</html>