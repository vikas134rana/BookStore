<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	

<div align="center">

	<img  src="/BookStoreWebsite/images/BookStoreLogo.png" width="350" height="96" />
	<img src="images/BookStoreLogo.png" width="350" height="96" />

	<div>
		<input type="text" width="50px"> <input type="submit"
			value="Search"> &nbsp;&nbsp;&nbsp; <a href="#">SigIn</a> | <a
			href="#">Register</a> | <a href="#">Cart</a>
	</div>

</div>

<div align="center">
	<c:if test="${categoryList!=null}">

		<c:forEach var="cat" items="${categoryList}" varStatus="status">
			<a href="#">${cat.name}</a> |
			</c:forEach>

	</c:if>
</div>
