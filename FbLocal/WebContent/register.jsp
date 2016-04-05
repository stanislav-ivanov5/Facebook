<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="./view/img/flat-avatar.png">
<title>ITTBook</title>
<!-- Bootstrap core CSS -->
<link href="./view/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet">
<link href="./view/assets/css/animate.min.css" rel="stylesheet">
<link href="./view/font-awesome-4.4.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="./view/assets/css/timeline.css" rel="stylesheet">
<script src="./view/assets/js/jquery.1.11.1.min.js"></script>
<script src="./view/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script src="./view/assets/js/custom.js"></script>
<script type="application/x-javascript">
	
	 addEventListener("load", function() {
    		setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); 
    		} 

</script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="welcome-page animated fadeIn">
	<div class="row row-welcome">
		<div class="login-page">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<img src="./view/img/prism.png" class="user-avatar">
					<h1>ITTBook</h1>

					<!--  ./register = view/register -->
					<form role="form" class="frm  animated flipInX" method="post"
						action="././register">
						<div class="form-content">

							
						<c:if test="${not empty invalid}">
    						<h4 style="color:white"><strong>
    						<c:out value="${invalid}" /></strong></h4> 
    					</c:if>
    				
   							<div class="form-group">
								<input type="text" class="form-control input-underline input-lg"
									placeholder="First name" name="first_name" required>
							</div>
							<div class="form-group">
								<input type="text" class="form-control input-underline input-lg"
									placeholder="Last name" name="last_name" required>
							</div>
							<div class="form-group">
								<input type="text" class="form-control input-underline input-lg"
									placeholder="Email" name="email" required>
							</div>
							<div class="form-group">
								<input type="password"
									class="form-control input-underline input-lg"
									placeholder="Password" name="password" required>
							</div>
							<div class="gender_font_size">
								<input type="radio" name="gender" value="male">Male&nbsp&nbsp&nbsp</input>
								<input type="radio" name="gender" value="female">Female</input>
							</div>
						</div>
						<a href="./login.jsp" class="btn btn-info btn-lg btn-frm">Log
							in</a> <input type="submit"
							class="btn btn-info btn-lg" value="Register" />
						</a>
					</form>
				</div>
			</div>
			<div class="row welcome-full animated fadeInLeft users-row">
				<div class="row-body hidden-xs hidden-sm">
					<!-- some registered users -->
					<div class="welcome-users-inner">
						<div class="welcome-user">
							<img src="./view/img/Friends/guy-3.jpg" class="img-rounded" />

						</div>
						<div class="welcome-user">
							<img src="./view/img/Friends/woman-1.jpg" class="img-rounded" />

						</div>
						<div class="welcome-user">
							<img src="./view/img/Friends/guy-2.jpg" class="img-rounded" />

						</div>
						<div class="welcome-user">
							<img src="./view/img/Friends/woman-2.jpg" class="img-rounded" />

						</div>
						<div class="welcome-user">
							<img src="./view/img/Friends/guy-5.jpg" class="img-rounded" />

						</div>
						<div class="welcome-user">
							<img src="./view/img/Friends/woman-3.jpg" class="img-rounded" />

						</div>
						<div class="welcome-user">
							<img src="./view/img/Friends/guy-8.jpg" class="img-rounded" />

						</div>
						<div class="welcome-user">
							<img src="./view/img/Friends/woman-4.jpg" class="img-rounded" />

						</div>
						<div class="welcome-user">
							<img src="./view/img/Friends/woman-4.jpg" class="img-rounded" />

						</div>
						<div class="welcome-user">
							<img src="./view/img/Friends/woman-7.jpg" class="img-rounded" />

						</div>
						<div class="welcome-user">
							<img src="./view/img/Friends/woman-7.jpg" class="img-rounded" />

						</div>
					</div>
					<!-- some registered users -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>