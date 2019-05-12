<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<form action="">
			<br>
			UserName: <input name="username"> <br>
			Password: <input name="password" type="password"><br>
			<input type="submit" value="Login">
		</form>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>