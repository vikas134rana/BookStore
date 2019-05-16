<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div align="center">

	<img src="images/BookStoreLogo.png" width="350" height="96" />

	<div>
		<br>
		<form action="search_book">
			<input type="text" width="50px" id="search" name="search"> <input
				type="submit" value="Search">
		</form>
		&nbsp;&nbsp;&nbsp; <a href="#">SigIn</a> | <a href="#">Register</a> |
		<a href="#">Cart</a>
	</div>
	<br>

</div>

<div align="center">
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
