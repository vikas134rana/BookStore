<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.bookstore.entity.Users" import="java.util.List"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Category Management</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">

		<div class='page_heading'>Category Management</div>

		<h3>
			<a href="category_form.jsp">Create New Category</a>
		</h3>

		<c:if test="${requestScope.message!=null}">
			<h4>
				<i>${requestScope.message}</i>
			</h4>
		</c:if>
		<table class= "list">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Category</th>
				<th>Actions</th>
			</tr>



			<c:forEach var="cat" items="${listCategory}" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${cat.categoryId}</td>
					<td>${cat.name}</td>
					<td><a href="edit_category?id=${cat.categoryId}">Edit</a>&nbsp;&nbsp;<a
						href="#" onclick="confirmDelete(${cat.categoryId})">Delete</a></td>
				</tr>
			</c:forEach>

		</table>

	</div>

	<jsp:directive.include file="footer.jsp" />

</body>

<script type="text/javascript">
	function confirmDelete(id) {
		if (confirm("Are you sure you want to delete Category with categoryId " + id)) {
			window.location = "delete_category?id="+id;
		}
	}
</script>

</html>