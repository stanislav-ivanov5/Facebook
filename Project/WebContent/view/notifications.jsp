<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.png">
    <title>People-Let</title>
    <!-- Bootstrap core CSS -->
    <link href="bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/animate.min.css" rel="stylesheet">
    <link href="font-awesome-4.4.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="assets/css/timeline.css" rel="stylesheet">
    <script src="assets/js/jquery.1.11.1.min.js"></script>
    <script src="bootstrap-3.3.5/js/bootstrap.min.js"></script>
    <script src="assets/js/custom.js"></script>
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
    <c:import url="./nav_bar.jsp"></c:import>       
			       
    <div class="row text-center color-container">
    	<h1 class="profile-name"><c:out value="${sessionScope.first_name}${' '}${sessionScope.last_name}" /></h1>
    </div>

    <!-- Timeline content -->
    <div class="row">
    <div class="container container-timeline" style="margin-top:50px;">
    	<div class="col-md-10 no-paddin-xs">
    		<div class="col-md-5 no-paddin-xs">
				<div class="panel panel-info">
				  <div class="panel-heading">
				    <h3 class="panel-title">Notifications</h3>
				  </div>
				  <div class="panel-body">
				  <c:if test="${not empty sessionScope.profilePic}">
							<img src="img/<c:out value="${sessionScope.profilePic}"></c:out>"
								class="home-avatar img-circle  img-thumbnail" alt="user profile image">
						</c:if> <c:if test="${empty sessionScope.profilePic}">
							<img src="img/user-default-pic.png" class="home-avatar img-circle  
							img-thumbnail" alt="user profile image">
						</c:if>
				  	
				  	<c:out value="${sessionScope.first_name}"></c:out>
				  </div>
				</div>

				<!-- friends -->
		
				<!-- People You May Know -->
				
			</div><!-- end left content -->

			<!-- notification list-->
			<c:if test="${not empty sessionScope.deletedRequest}">
				<i style="font-size:25px; color:#39bbdb;"><c:out value="${sessionScope.deletedRequest}"></c:out></i><br/>
				<c:set var="deletedRequest" value="${null}" scope="session"></c:set>
			</c:if>
			
			<c:if test="${not empty sessionScope.acceptRequest}">
				<i style="font-size:25px; color:#39bbdb;"><c:out value="${sessionScope.acceptRequest}"></c:out></i><br/>
				<c:set var="acceptRequest" value="${null}" scope="session"></c:set>
			</c:if>
			<c:if test="${not empty sessionScope.listPeople}">
			<c:forEach items="${sessionScope.listPeople}" var="person">
			
    		<div class="col-md-7 no-paddin-xs">
				<div class="panel panel-white post panel-shadow">
				  <div class="post-heading">			
				      <div class="pull-left meta">
				          <div class="title h5">
				              <a href="#" class="post-user-name"><c:out value="${person.firstName}${' '}${person.lastName}"/></a>
				              send you a friendship request 
				          </div>
				          <br>
				          <h5 ><form style=" display: inline-block;" action="../acceptDecline" method="post">
				          <input type="hidden" name="decision" value="true">
				         <input type="submit" value="Accept" style="background-color:#39bbdb; color:white">
				         <input type= "hidden" name= "senderEmail" value="${person.email}">
				           </form>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        	<form style="display: inline-block;" action="../acceptDecline" method="post">
				        	   <input type="hidden" name="decision" value="false">
				        	  <input type= "hidden" name= "senderEmail" value="${person.email}">
						<input type="submit" value="Decline" style="background-color:#39bbdb;color:white">
						</form>  </h5></div>				         
				      </div>
				  </div>
				</div>
    		</div></c:forEach></c:if>
    		<c:if test="${ empty sessionScope.listPeople }">
    			<i style="font-size:25px; color:#39bbdb;"><c:out value=" You don't have any notifications."></c:out></i>
    		</c:if>
    		<!-- notification list-->
    	</div>
    </div>
    </div>

    <!-- Online users sidebar content--
    <div class="chat-sidebar focus">
      <div class="list-group text-left">
        <p class="text-center visible-xs"><a href="#" class="hide-chat btn btn-success">Hide</a></p> 
        <p class="text-center chat-title">Online users</p>  
        <a href="messages.html" class="list-group-item">
          <i class="fa fa-check-circle connected-status"></i>
          <img src="img/Friends/guy-2.jpg" class="img-chat img-thumbnail">
          <span class="chat-user-name">Jeferh Smith</span>
        </a>
        <a href="messages.html" class="list-group-item">
          <i class="fa fa-times-circle absent-status"></i>
          <img src="img/Friends/woman-1.jpg" class="img-chat img-thumbnail">
          <span class="chat-user-name">Dapibus acatar</span>
        </a>
        <a href="messages.html" class="list-group-item">
          <i class="fa fa-check-circle connected-status"></i>
          <img src="img/Friends/guy-3.jpg" class="img-chat img-thumbnail">
          <span class="chat-user-name">Antony andrew lobghi</span>
        </a>
        <a href="messages.html" class="list-group-item">
          <i class="fa fa-check-circle connected-status"></i>
          <img src="img/Friends/woman-2.jpg" class="img-chat img-thumbnail">
          <span class="chat-user-name">Maria fernanda coronel</span>
        </a>
        <a href="messages.html" class="list-group-item">
          <i class="fa fa-check-circle connected-status"></i>
          <img src="img/Friends/guy-4.jpg" class="img-chat img-thumbnail">
          <span class="chat-user-name">Markton contz</span>
        </a>
        <a href="messages.html" class="list-group-item">
          <i class="fa fa-times-circle absent-status"></i>
          <img src="img/Friends/woman-3.jpg" class="img-chat img-thumbnail">
          <span class="chat-user-name">Martha creaw</span>
        </a>
        <a href="messages.html" class="list-group-item">
          <i class="fa fa-times-circle absent-status"></i>
          <img src="img/Friends/woman-8.jpg" class="img-chat img-thumbnail">
          <span class="chat-user-name">Yira Cartmen</span>
        </a>
        <a href="messages.html" class="list-group-item">
          <i class="fa fa-check-circle connected-status"></i>
          <img src="img/Friends/woman-4.jpg" class="img-chat img-thumbnail">
          <span class="chat-user-name">Jhoanath matew</span>
        </a>
        <a href="messages.html" class="list-group-item">
          <i class="fa fa-check-circle connected-status"></i>
          <img src="img/Friends/woman-5.jpg" class="img-chat img-thumbnail">
          <span class="chat-user-name">Ryanah Haywofd</span>
        </a>
        <a href="messages.html" class="list-group-item">
          <i class="fa fa-check-circle connected-status"></i>
          <img src="img/Friends/woman-9.jpg" class="img-chat img-thumbnail">
          <span class="chat-user-name">Linda palma</span>
        </a>
        <a href="messages.html" class="list-group-item">
          <i class="fa fa-check-circle connected-status"></i>
          <img src="img/Friends/woman-10.jpg" class="img-chat img-thumbnail">
          <span class="chat-user-name">Andrea ramos</span>
        </a>
        <a href="messages.html" class="list-group-item">
          <i class="fa fa-check-circle connected-status"></i>
          <img src="img/Friends/child-1.jpg" class="img-chat img-thumbnail">
          <span class="chat-user-name">Dora ty bluekl</span>
        </a>        
      </div>
    </div><!-- Online users sidebar content-->
<% } else { response.sendRedirect("../login.jsp"); } %>
  </body>
</html>
