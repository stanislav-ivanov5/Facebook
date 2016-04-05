<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
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
				<a class="navbar-brand" href="index.html"> <b>ITTBOOK</b>
				</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<div class="col-md-5 col-sm-3">
					<form class="navbar-form">
						<div class="form-group" style="display: inline;">
							<div class="input-group" style="display: table;">
								<input class="form-control" name="search"
									placeholder="Search..." autocomplete="off" type="text">
								<span class="input-group-addon" style="width: 1%;"> <span
									class="glyphicon glyphicon-search"></span>
								</span>
							</div>
						</div>
					</form>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<li class="active">
					<a href="./profile.jsp">
					<!--  Display user names in navbar -->
					<c:out value="${sessionScope.first_name}${' '}${sessionScope.last_name}"/>
					
					<!--  Display user profile pic in navbar -->
					<c:if test="${not empty sessionScope.profile_picture_name}">
						 	<img src="img/<c:out value="${sessionScope.profile_picture_name}"></c:out>" class="img-nav">
					</c:if>
					<c:if test="${empty sessionScope.profile_picture_name}">
						 	<img src="img/user-default-pic.png" class="img-nav">
					</c:if>

					
					</a></li>
					<li><a href="./home.html"><i class="fa fa-bars"></i>&nbsp;Home</a></li>
					<li><a href="./messages.html"><i class="fa fa-comments"></i></a></li>
					<li><a href="./notifications.html"><i class="fa fa-globe"></i></a></li>
					<li><a href="./search.html"><i class="fa fa-users"></i></a></li>
					<li><a href="./about.html"><i class="fa fa-info-circle"></i></a></li>
					<li><a href="#" class="nav-controller"><i
							class="fa fa-user"></i></a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="row text-center color-container">
		<h1 class="profile-name">
			<!--  Display user names in left pane -->
			<c:out value="${first_name}${' '}${last_name}" />
		</h1>
	</div>
	<!-- Timeline content -->
	<div class="row">
		<div class="container" style="margin-top: 50px;">
			<div class="row">
				<div class="col-md-10 no-paddin-xs">
					<div class="profile-nav col-md-4">
						<div class="panel">
							<!--  Profile picture at left side pane -->
							
							<div class="user-heading round">
                     			<a href="#">
                     				<c:if test="${empty sessionScope.profile_picture_name}" >
                          				<img src="./img/user-default-pic.png" alt="">
                          			</c:if>
                          				
                          			<c:if test="${not empty sessionScope.profile_picture_name}">
                          				<img src="img/<c:out value="${sessionScope.profile_picture_name}"></c:out>" alt="" >
                          			</c:if>
                     			</a>
                      			<h1>
                     		  		<c:out value="${sessionScope.first_name}${' '}${sessionScope.last_name}" />
                     	 		</h1>
                     			<p>
                     	 			<c:out value="${sessionScope.email}" />
                    	  		</p>
                 		 	</div>

							<ul class="nav nav-pills nav-stacked">
								<li><a href="./profile.jsp"> <i class="fa fa-user"></i>
										Profile
								</a></li>
								<li><a href="../about.jsp"> <i class="fa fa-info-circle"></i>
										About
								</a></li>
								<li><a href="./view/friends.html"> <i class="fa fa-users"></i>
										Friends
								</a></li>
								<li><a href="./photos.jsp"> <i
										class="fa fa-file-image-o"></i> Photos
								</a></li>
								<li class="active"><a href="edit-profile.html"> <i
										class="fa fa-edit"></i> Edit profile
								</a></li>
							</ul>
						</div>
					</div>
					<div class="profile-info col-md-8">
						<!-- update info -->
						<div class="panel panel-info post panel-shadow">
							<div class="panel-heading">
								<h3 class="panel-title">Edit info</h3>
							</div>
							<div class="panel-body">
							<form action="../EditPerson" method="post">
								<div class="form-group">
									
										<label class="col-md-3 control-label">First name</label>
										<div class="col-md-8">
											<input class="form-control" type="text" name="first_name">
										</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Last name</label>
									<div class="col-md-8">
										<input class="form-control" type="text" name="last_name">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Location</label>
									<div class="col-md-8">
										<input class="form-control" type="text" value="" name="location">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Phone</label>
									<div class="col-md-8">
										<input class="form-control" type="text" name="phone">
									</div>				
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">About</label>
									<div class="col-md-8" >
										<textarea style="border-radius:3px;"  
										placeholder="Tell us more about you." cols="47" rows="4" name="about"></textarea>
									</div>
								</div>
								<div class="form-group">
									<input type="submit" class="btn btn-info" value="Update"/>
									</form>
								</div>
							</div>
						</div>
						<!-- end update info-->
						
						<!-- start upload photo -->
						<div class="panel panel-info post panel-shadow">
							<div class="panel-heading">
								<h3 class="panel-title">Upload photo</h3>
							</div>
							
							<div class="panel-body">
							
							<!-- Upload photo -->
							
							<c:if test="${not empty sessionScope.uploadedPhoto}">
								<c:out value="${sessionScope.uploadedPhoto}"></c:out>
								<c:set var="uploadedPhoto" value="${null}" scope="session"  />
							</c:if>
							
								<form action="../upload" method="post" enctype="multipart/form-data">
								<div class="form-group">
									<label class="col-md-4 control-label">Profile photo</label>
									<div class="col-md-7">
											<input type="file" name="profile-photo"  />
									</div><br />
								</div>
								<div class="form-group">
									<input type="submit" class="btn btn-info" value="Upload">
								</div>
								</form>
							</div>
						</div>
						<!-- end upload photo -->
						
						<!-- update info -->
						<div class="panel panel-info post panel-shadow">
							<div class="panel-heading">
								<h3 class="panel-title">Change password</h3>
							</div>
							
							<div class="panel-body">
							
							<c:if test="${not empty sessionScope.invalidPassword}">
								<strong><c:out value="${sessionScope.invalidPassword}"></c:out></strong>
								<c:set var="invalidPassword" value="${null}" scope="session"  />
							</c:if>
							
							<c:if test="${not empty sessionScope.validPassword}">
								<strong><c:out value="${sessionScope.validPassword}"></c:out></strong>
								<c:set var="validPassword" value="${null}" scope="session"  />
							</c:if>
							
								<form action="../change-password" method="post">
								<div class="form-group">
									<label class="col-md-4 control-label">Current password</label>
									<div class="col-md-7">
										<input class="form-control" type="text" value="" name="currentPassword">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">New password</label>
									<div class="col-md-7">
										<input class="form-control" type="text" value="" name="newPassword">
									</div>
								</div>
								<div class="form-group">
									<input type="submit" class="btn btn-info" value="Change Password">
								</div>
								</form>
							</div>
						</div>
						<!-- end update info-->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--End Timeline content -->

	<!-- Online users sidebar content -->
	<div class="chat-sidebar focus">
		<div class="list-group text-left">
			<p class="text-center visible-xs">
				<a href="#" class="hide-chat btn btn-success">Hide</a>
			</p>
			<p class="text-center chat-title">Online users</p>
			<a href="messages.html" class="list-group-item"> <i
				class="fa fa-check-circle connected-status"></i> <img
				src="./view/img/Friends/guy-2.jpg" class="img-chat img-thumbnail"> <span
				class="chat-user-name">Jeferh Smith</span>
			</a> <a href="./view/messages.html" class="list-group-item"> <i
				class="fa fa-times-circle absent-status"></i> <img
				src="./view/img/Friends/woman-1.jpg" class="img-chat img-thumbnail">
				<span class="chat-user-name">Dapibus acatar</span>
			</a> <a href="./view/messages.html" class="list-group-item"> <i
				class="fa fa-check-circle connected-status"></i> <img
				src="./view/img/Friends/guy-3.jpg" class="img-chat img-thumbnail"> <span
				class="chat-user-name">Antony andrew lobghi</span>
			</a> <a href="./view/messages.html" class="list-group-item"> <i
				class="fa fa-check-circle connected-status"></i> <img
				src="img/Friends/woman-2.jpg" class="img-chat img-thumbnail">
				<span class="chat-user-name">Maria fernanda coronel</span>
			</a> <a href="messages.html" class="list-group-item"> <i
				class="fa fa-check-circle connected-status"></i> <img
				src="img/Friends/guy-4.jpg" class="img-chat img-thumbnail"> <span
				class="chat-user-name">Markton contz</span>
			</a> <a href="messages.html" class="list-group-item"> <i
				class="fa fa-times-circle absent-status"></i> <img
				src="img/Friends/woman-3.jpg" class="img-chat img-thumbnail">
				<span class="chat-user-name">Martha creaw</span>
			</a> <a href="messages.html" class="list-group-item"> <i
				class="fa fa-times-circle absent-status"></i> <img
				src="img/Friends/woman-8.jpg" class="img-chat img-thumbnail">
				<span class="chat-user-name">Yira Cartmen</span>
			</a> <a href="messages.html" class="list-group-item"> <i
				class="fa fa-check-circle connected-status"></i> <img
				src="img/Friends/woman-4.jpg" class="img-chat img-thumbnail">
				<span class="chat-user-name">Jhoanath matew</span>
			</a> <a href="messages.html" class="list-group-item"> <i
				class="fa fa-check-circle connected-status"></i> <img
				src="./view/img/Friends/woman-5.jpg" class="img-chat img-thumbnail">
				<span class="chat-user-name">Ryanah Haywofd</span>
			</a> <a href="messages.html" class="list-group-item"> <i
				class="fa fa-check-circle connected-status"></i> <img
				src="img/Friends/woman-9.jpg" class="img-chat img-thumbnail">
				<span class="chat-user-name">Linda palma</span>
			</a> <a href="messages.html" class="list-group-item"> <i
				class="fa fa-check-circle connected-status"></i> <img
				src="img/Friends/woman-10.jpg" class="img-chat img-thumbnail">
				<span class="chat-user-name">Andrea ramos</span>
			</a> <a href="messages.html" class="list-group-item"> <i
				class="fa fa-check-circle connected-status"></i> <img
				src="img/Friends/child-1.jpg" class="img-chat img-thumbnail">
				<span class="chat-user-name">Dora ty bluekl</span>
			</a>
		</div>
	</div>
	<!-- Online users sidebar content-->

	<footer class="welcome-footer">
		<div class="container">
			<p>
			<div class="footer-links">
				<a href="#">Terms of Use</a> | <a href="#">Privacy Policy</a> | <a
					href="#">Developers</a> | <a href="#">Contact</a> | <a href="#">About</a>
			</div>
			Copyright &copy; Company - All rights reserved
			</p>
		</div>
	</footer>
</body>
</html>