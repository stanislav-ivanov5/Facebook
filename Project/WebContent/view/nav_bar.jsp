<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="img/favicon.png">
<title>ITTBOOK</title>
<!-- Bootstrap core CSS -->
<link href="./bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet">
<link href="./assets/css/animate.min.css" rel="stylesheet">
<link href="./font-awesome-4.4.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="./assets/css/timeline.css" rel="stylesheet">
<script src="./assets/js/jquery.1.11.1.min.js"></script>
<script src="./bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script src="./assets/js/custom.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="animated fadeIn">
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<% if (session.getAttribute("email") != null) {  %>
	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top navbar-principal">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="../getAllPosts"> <b>ITTBOOK</b>
			</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<div class="col-md-4 col-sm-3">
				<form action="../././search" method="get" class="navbar-form">
					<div class="form-group" style="display: inline-block;">
						<div class="input-group" style="display: inline-block;">
							<span class="input-group-addon" style="width: 1%;"> <input
								type="text" name="search" class="input-group form-control"
								placeholder="Search..." autocomplete="off"> <!--  <span class="glyphicon-search">  -->
								<input type="submit" value="Go"
								class="form-group form-control"> <!-- </span>  -->
							</span>
						</div>
					</div>
				</form>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="../getAllPosts"> <!--  Display user names in navbar -->
						<c:out value="${sessionScope.first_name}" /> <!--  Display user profile pic in navbar -->
						<c:if test="${not empty sessionScope.profilePic}">
							<img
								src="img/<c:out value="${sessionScope.profilePic}"></c:out>"
								class="img-nav">
						</c:if> <c:if test="${empty sessionScope.profilePic}">
							<img src="img/user-default-pic.png" class="img-nav">
						</c:if>

				</a></li>
				<li><a href="../getAllPosts"><i class="fa fa-bars"></i>&nbsp;Home</a></li>
				<c:if test="${not empty sessionScope.hasNotifications }">
				
				<li><a href="../notifications"><i style="color:#FF1111;" class="fa fa-globe"></i></a></li>
				
				</c:if>
				<c:if test="${empty sessionScope.hasNotifications }">
				
				<li><a href="../notifications"><i class="fa fa-globe"></i></a></li>
				
				</c:if>
				<li><a href="../displayFriends"><i class="fa fa-users"></i></a></li>
				<li><a href="./about.jsp"><i class="fa fa-info-circle"></i></a></li>
				<li><a href="../logout"><i class="fa fa-bars"></i><strong>&nbsp;Logout</strong></a></li>

			</ul>
		</div>
	</div>
	</nav>

	<% } else { response.sendRedirect("../login.jsp"); } %>

</body>
</html>