<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>

</head>
<body class="animated fadeIn">
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<% if (session.getAttribute("email") != null) {  %>
	<c:import url="./nav_bar.jsp"></c:import>
	<div class="row text-center color-container">
		<h1 class="profile-name"><c:out value="${sessionScope.first_name}${' '}${sessionScope.last_name}"></c:out></h1>
	</div>

	<!-- Timeline content -->
	<div class="row">
		<div class="container">
			<div class="row">
				<hr>
				<div class="directory-info-row">
					<div class="row">
					
					<!--  Map with search results -->
					<c:if test="${empty sessionScope.searchResults}">
							<h4 style="margin-left:3em;">We couldn't find any results matching your criteria.</h4>
					</c:if>
					<c:forEach var="entry" items="${sessionScope.searchResults}">
						<div class="col-md-6 col-sm-6">
							<div class="panel">
								<div class="panel-body">
								<c:if test="${not empty sessionScope.requestRepetition }">
									<c:out value="${sessionScope.requestRepetition}"></c:out>
									<c:set var="requestRepetition" value="${null}" scope="session"></c:set>
								</c:if>
								
									<div class="media">
										
										<a class="pull-left" href="#">
											
											<c:forEach var="attribute" items="${entry.value}">
												<c:if test="${ attribute.key == 'profile_picture'}" >
													<c:set var="pic" value="${attribute.value }" />
												</c:if>
											
												
											</c:forEach>
																						
											
										<c:choose>		
											<c:when test="${ empty pic }">
												<img class="thumb media-object" src="./img/user-default-pic.png" alt="">
												<c:set var="pic" value="${null}" />
											</c:when>
											<c:when test="${ not empty pic }">
												<c:out value="${ attribute.key }" />
												<c:out value="${ attribute.value }" />
											
												<img class="thumb media-object" src="
													img/<c:out value="${ pic }"/>" alt="">
													<c:set var="pic" value="${null}" />
											</c:when>
										</c:choose>		
										</a>	
										<div class="media-body">
											<h4>
												
												<!--  Get the value attributes from the List  -->
												<c:forEach var="attribute" items="${entry.value}" >
  													
  													
  										
  														<c:if test="${ attribute.key != 'profile_picture' }">
  														
  														
  															<c:out value="${ attribute.value }" />
															<c:if test="${ attribute.key  == 'last_name' }">
  																<br />
  															</c:if>
  														</c:if>
  													
												</c:forEach>
												
												
											</h4>
										</div>
									
										<c:set var="emailOfTOSendtoServlet" value="${entry.key}" scope= "page"></c:set>
										<form action="../friendshipRequest" method="get">
											<div class="widget-text-box " style="border:none">											
												<input type="submit" value="Send a friendship request" name="sendFriendRequest"  class="btn btn-sm btn-primary" 
												style="background:#d0d0d0;">
												<i class="fa fa-user-plus"></i> 
											<input type="hidden" name="personToSendRequestTo" value="${emailOfTOSendtoServlet}">
											</div></form>

									</div>
								</div>
							</div>
						</div>
						</c:forEach>

						
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- end timeline content-->

	
	<!-- Online users sidebar content-->
<% } else { response.sendRedirect("../login.jsp"); } %>
</body>
</html>