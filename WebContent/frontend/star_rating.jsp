<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title></title>

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
	integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay"
	crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>

<style>
.star {
	position: relative;
	display: inline-block;
	font-size: 25px;
}

.star-under {
	color: #ddd;
}

.star-over {
	color: #f80;
	overflow: hidden;
	position: absolute;
	top: 0;
	left: 0;
	display: none;
}

.star-visible {
	display: inline-block;
}
</style>



</head>
<body>

	<span > <i class="star star-under fa fa-star">
			<i class="star star-over fa fa-star"></i>
	</i> <i class="star star-under fa fa-star"> <i
			class="star star-over fa fa-star"></i>
	</i> <i class="star star-under fa fa-star"> <i
			class="star star-over fa fa-star"></i>
	</i> <i class="star star-under fa fa-star"> <i
			class="star star-over fa fa-star"></i>
	</i> <i class="star star-under fa fa-star"> <i
			class="star star-over fa fa-star"></i>
	</i>
	</span>

	<script type="text/javascript">
		
			<% String rating = request.getParameter("rating"); %>
			var rating = <%=rating%>;
			 
			<% String id = request.getParameter("ratingContainerId");%>
			var id = '<%=id%>';
			
			rateStyle(rating, id);
			
		// jquery
		function rateStyleJQ(num, divID) {
			var ratingRounded = Math.floor(num);
			$("#" + divID + " .star-over").slice(0, ratingRounded).addClass('star-visible');
			var partialShade = Math.round((num - ratingRounded) * 100);
			if (partialShade != 0) {
				$($("#" + divID + " .star-over").get(ratingRounded)).addClass('star-visible').css("width", partialShade + "%");
			}
		}

		// javascript
		function rateStyle(num, divID) {
			var ratingRounded = Math.floor(num);
			var starArray = document.getElementById(divID).querySelectorAll(".star-over");
			for (var i = 0; i < ratingRounded; i++) {
				starArray[i].classList.add("star-visible");
			}
			var finalStar = Math.round((num - ratingRounded) * 100);
			if (finalStar != 0) {
				starArray[ratingRounded].classList.add("star-visible");
				starArray[ratingRounded].style.width = finalStar + "%";
			}
		}
	</script>
</body>
</html>