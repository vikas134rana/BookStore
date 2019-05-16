<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">

		<c:if test="${user==null}">
			<div class='page_heading'>Create User</div>
			<form id="user_form" method="post" action="create_user">
		</c:if>

		<c:if test="${user!=null}">
			<div class='page_heading'>Edit User</div>
			<form id="user_form" method="post" action="update_user">
		</c:if>

		<input type="hidden" id="userId" name="userId" value="${user.userId}">
		<table>
			<tr>
				<td>UserName:</td>
				<td><input type="text" id="username" name="username"
					value="${user.fullName}"></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="email" id="email" name="email"
					value="${user.email}"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" id="password" name="password"
					value=""></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button"
					value="Cancel" onclick="javascript:history.go(-1);"> <input
					type="submit" value="Submit"></td>
			</tr>
		</table>

		</form>

	</div>

	<jsp:directive.include file="footer.jsp" />

</body>
<script>
	
	$().ready(function(){
		$("#user_form").validate({
			
			rules:{
				username: "required",
				email: {
					required:true,
					email:true
				},
				
				<c:if test="${user==null}">
					password: "required"
				</c:if>
			},
			
			messages:{
				username: "Please enter the Username",
				email: {
					required: "Please enter the Email",
					email: "Please enter the valid Email"
				},
				password: "Please enter the Password"
			}
			
		});
	});
	
</script>
</html>