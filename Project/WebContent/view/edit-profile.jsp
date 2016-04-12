<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<!-- Fixed navbar -->
	<c:import url="./nav_bar.jsp"></c:import>
	<div class="row text-center color-container">
		<h1 class="profile-name">
			<!--  Display user names in left pane -->
			<c:out value="${first_name}${' '}${last_name}" />
		</h1>
	</div>
	<!-- Timeline content -->
	<c:import url="./left_profile_bar.jsp"></c:import>

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
								<h3 class="panel-title">Upload profile picture</h3>
							</div>
							
							<div class="panel-body">
							
							<!-- Upload  profile photo -->
							
							<c:if test="${not empty sessionScope.uploadedPhoto}">
								<c:out value="${sessionScope.uploadedPhoto}"></c:out>
								<c:set var="uploadedPhoto" value="${null}" scope="session"  />
							</c:if>
							
								<form action="../upload" method="post" enctype="multipart/form-data">
								<div class="form-group">
									<label class="col-md-4 control-label">Profile picture</label>
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
						<!-- end upload profile photo -->
							<c:if test="${not empty sessionScope.uploadedCover}">
								<c:out value="${sessionScope.uploadedCover}"></c:out>
								<c:set var="uploadedCover" value="${null}" scope="session"  />
							</c:if>
						<div class="panel panel-info post panel-shadow">
							<div class="panel-heading">
								<h3 class="panel-title">Upload cover picture</h3>
							</div>
							
							<div class="panel-body">
							
							<!-- Upload cover photo -->
							
							<c:if test="${not empty sessionScope.uploadedPhoto}">
								<c:out value="${sessionScope.uploadedPhoto}"></c:out>
								<c:set var="uploadedPhoto" value="${null}" scope="session"  />
							</c:if>
							
								<form action="../uploadCover" method="post" enctype="multipart/form-data">
								<div class="form-group">
									<label class="col-md-4 control-label">Cover picture</label>
									<div class="col-md-7">
											<input type="file" name="coverPhoto"  />
									</div><br />
								</div>
								<div class="form-group">
									<input type="submit" class="btn btn-info" value="Upload">
								</div>
								</form>
							</div>
						</div>
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


	<!-- Online users sidebar content-->
<% } else { response.sendRedirect("./login.jsp"); } %>
</body>
</html>
