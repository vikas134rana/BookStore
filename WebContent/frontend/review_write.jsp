<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center" style="padding: 10px;">

		<div style="font-size: 18px;">
			<span><b>Review</b> by </span> <span> <b>${customer.fullName}</b>
			</span>
		</div>

		<div style="margin-bottom: 10px;"></div>



		<div style="display: inline-block;">

			<form id = "review_form" action="submit_review" method="post">

				<input type="hidden" name="bookId" id="bookId"
					value="${book.bookId}">

				<table cellpadding="10">

					<tr>
						<td rowspan="4"><div style="display: inline-block;"
								align="center">

								<div>
									<b>${book.title}</b>
								</div>
								<br>
								<div>
									<img alt="" id="thumbnail" name="thumbnail" style="width: 40%"
										src="data:image/png;base64,${book.imageBase64}"><br>
								</div>

							</div></td>
						<td><b>Rating</b></td>
						<td>
							<div id="rateYo"></div> <input type="hidden" id="rating"
							name="rating">
						</td>
					</tr>

					<tr>
						<td><b>Headline</b></td>
						<td><input id="headline" name="headline"
							placeholder="Headline"></td>
					</tr>

					<tr>
						<td><b>Comment</b></td>
						<td><textarea id="comment" name="comment"
								placeholder="Comment" rows="8" cols="22"></textarea></td>
					</tr>


					<tr>
						<td colspan="2" align="center"><input type="button"
							value="Cancel" onclick="javascript:history.go(-1);"> <input
							type="submit" value="Submit"></td>
					</tr>

				</table>

			</form>

		</div>

	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
<script>
	$().ready(function() {

		$("#review_form").validate({

			rules : {
				rating : "required",
				headline : "required",
				comment : "required"
			}

		});

		$("#rateYo").rateYo({
			starWidth : "30px",
			fullStar : true,
			onSet : function(rating, rateYoInstance) {
				$("#rating").val(rating);
			}
		});
	});
</script>
</html>