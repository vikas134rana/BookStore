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

	<div>
		<div align="center">
			<h2>New Books</h2>
			<c:forEach var="book" items="${newBookList}" varStatus="status">
				<div
					style="width: 230px; display: inline-block; border: 1px solid gray; border-radius: 10px; padding: 15px; margin-right: 20px;">
					<div>
						<a href="view_book?id=${book.bookId}"><img alt=""
							src="data:image/png;base64,${book.imageBase64}"
							style="width: 150px; height: 200px"></a>
					</div>
					<div style="font-size: 18px; height: 50px;">
						<a href="view_book?id=${book.bookId}"><b>${book.title}</b></a>
					</div>
					<div>
						<i>by ${book.author}</i>
					</div>
					<div>
						<b>$${book.price}</b>
					</div>
					<div>
						<span id="star_rating_new_book${book.bookId}" style="width: 50px;">
							<jsp:include page="star_rating.jsp">
								<jsp:param name="ratingContainerId"
									value="star_rating_new_book${book.bookId}" />
								<jsp:param name="rating" value="${book.ratingAvg}" />
							</jsp:include>
						</span>
					</div>
				</div>
			</c:forEach>
			<br> <br> <br> <br>
		</div>

		<div align="center">
			<h2>Best-Selling Books</h2>
			<c:forEach var="book" items="${bestSellingBookList}"
				varStatus="status">
				<div
					style="width: 230px; display: inline-block; border: 1px solid gray; border-radius: 10px; padding: 15px; margin-right: 20px;">
					<div>
						<a href="view_book?id=${book.bookId}"><img alt=""
							src="data:image/png;base64,${book.imageBase64}"
							style="width: 150px; height: 200px"></a>
					</div>
					<div style="font-size: 18px; height: 50px;">
						<a href="view_book?id=${book.bookId}"><b>${book.title}</b></a>
					</div>
					<div>
						<i>by ${book.author}</i>
					</div>
					<div>
						<b>$${book.price}</b>
					</div>
					<div>
						<span id="star_rating_best_selling_book${book.bookId}"
							style="width: 50px;"> <jsp:include page="star_rating.jsp">
								<jsp:param name="ratingContainerId"
									value="star_rating_best_selling_book${book.bookId}" />
								<jsp:param name="rating" value="${book.ratingAvg}" />
							</jsp:include>
						</span>
					</div>
				</div>
			</c:forEach>
			<br> <br> <br> <br>
		</div>

		<div align="center">
			<h2>Most Favored Books</h2>
			<c:forEach var="book1" items="${mostFavouredBookList}"
				varStatus="status">
				<div
					style="width: 230px; display: inline-block; border: 1px solid gray; border-radius: 10px; padding: 15px; margin-right: 20px;">
					<div>
						<a href="view_book?id=${book1.bookId}"><img alt=""
							src="data:image/png;base64,${book1.imageBase64}"
							style="width: 150px; height: 200px"></a>
					</div>
					<div style="font-size: 18px; height: 50px;">
						<a href="view_book?id=${book1.bookId}"><b>${book1.title}</b></a>
					</div>
					<div>
						<i>by ${book1.author}</i>
					</div>
					<div>
						<b>$${book.price}</b>
					</div>
					<div>
						<span id="star_rating_most_favoured_book${book1.bookId}"
							style="width: 50px;"> <jsp:include page="star_rating.jsp">
								<jsp:param name="ratingContainerId"
									value="star_rating_most_favoured_book${book1.bookId}" />
								<jsp:param name="rating" value="${book1.ratingAvg}" />
							</jsp:include>
						</span>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>