<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h3>New Books</h3>
		<h3>Best-Selling Books</h3>
		<h3>Most Favored Books</h3>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>