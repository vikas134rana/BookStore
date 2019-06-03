<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div align="center">

	<a href="${pageContext.request.contextPath}"><img
		src="images/BookStoreLogo.png" width="350" height="96" /></a>

	<div>
		<br>
		<div style="display: inline-block;">
			<form action="search_book">
				<input type="text" width="50px" id="search" name="search"> <input
					type="submit" value="Search">
			</form>
		</div>
		&nbsp;&nbsp;&nbsp;

		<div style="display: inline-block;">
			<c:if test="${customer==null}">
				<a href="login">Login</a> | <a href="register">Register</a> |
		<a href="view_cart">Cart</a>
			</c:if>
			<c:if test="${customer!=null}">
				<a href="view_profile">Welcome, ${customer.fullName}</a> | <a
					href="logout">Logout</a> |
		<a href="my_orders">My Orders</a> | <a href="view_cart">Cart</a>
			</c:if>

		</div>
	</div>
	<br>

</div>

<div align="center" style="margin-bottom: 20px;">
	<c:if test="${categoryList!=null}">
		<div id="category_menu">
			<c:forEach var="cat" items="${categoryList}" varStatus="status">

				<a class="category_menu_item"
					href="view_category?id=${cat.categoryId}">${cat.name}</a>

				<c:if test="${!status.last}">|</c:if>
			</c:forEach>
		</div>
	</c:if>
</div>
