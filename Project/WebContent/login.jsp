<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");//HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", -1); //prevents caching at the proxy server
%>                          
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="./view/img/flat-avatar.png">
<title>ITTBook</title>
<!-- H1 tags color #5bc0de -->
<!-- Bootstrap core CSS -->
<link href="./view/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet">
<link href="./view/assets/css/animate.min.css" rel="stylesheet">
<link href="./view/font-awesome-4.4.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="./view/assets/css/timeline.css" rel="stylesheet">
<script src="./view/assets/js/jquery.1.11.1.min.js"></script>
<script src="./view/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script src="./view/assets/js/custom.js"></script>
<script type="./view/application/x-javascript">
	
	 addEventListener("load", function() {
    		setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); 
    		} 

</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body class="welcome-page animated fadeIn">
	<div class="row row-welcome">
		<div class="login-page">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<img src="./view/img/prism.png" class="user-avatar">
					<h1>ITTBook</h1>
					
					
					<c:if test="${not empty invalid}">
    						<h4 style="color:white"><strong>
    						<c:out value="${invalid}" /></strong></h4> 
    				</c:if>

					<form role="form" class="frm  animated flipInX" method="post"
						action="./login">
						<div class="form-content">
							<div class="form-group">
								<input type="text" class="form-control input-underline input-lg"
									placeholder="Email" name="email" required />
							</div>
							<div class="form-group">
								<input type="password"
									class="form-control input-underline input-lg"
									placeholder="Password" name="password" required />
							</div>
						</div>
						<input type="submit" class="btn btn-info btn-lg" value="Log in" />
						<a href="./register.jsp" class="btn btn-info btn-lg btn-frm">Register</a>
					</form>


				</div>
			</div>
</body>
</html>