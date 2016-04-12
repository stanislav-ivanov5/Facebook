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
    <!-- Fixed navbar -->
   	<c:import url="./nav_bar.jsp"></c:import>
    
    <div class="row text-center color-container">
      	<h1 class="profile-name">
      
    	  <c:out value="${sessionScope.first_name}${' '}${sessionScope.last_name}" />
		</h1>
   	
    </div>
      <c:import url="./left_profile_bar.jsp"></c:import>
              <div class="profile-info col-md-8">
             
              
                <div class="panel">
                    <div class="panel-body bio-graph-info">
                        <h1>Personal Information</h1>
                        <div class="row">
                            <div class="bio-row">
                                <p><span><strong>First Name</strong> </span> 
                                <c:out value="${sessionScope.first_name}" /> 
                                </p>
                            </div>
                            <div class="bio-row">
                                <p><span><strong>Last Name</strong> </span> <c:out value="${sessionScope.last_name}" /> </p>
                            </div>
                            <div class="bio-row">
                                <p><strong><span>Location </span></strong> 
                                
								<c:if test="${not empty sessionScope.location}">
    								<c:out value="${sessionScope.location}" />
    							</c:if></p>
                            </div>
                            <div class="bio-row">
                                <p><span><strong>Gender</strong></span>
                                
                                <c:if test="${not empty sessionScope.isMale}">
    								<c:out value="${sessionScope.isMale}" />
    							</c:if></p>
                            </div>
                            <div class="bio-row">
                                <p><span><strong>Email</strong> </span> 
                                <c:if test="${not empty sessionScope.email}">
    								<c:out value="${sessionScope.email}" />
    							</c:if></p>
                            </div>
                            
                            <div class="bio-row">
                                <p><span><strong>Phone</strong></span> 
                                <c:if test="${not empty sessionScope.phone}">
    								<c:out value="${sessionScope.phone}" />
    							</c:if></p>
                            </div>
                            <div class="bio-row">
                                <p><span><strong>About</strong> </span><br /> 
                                <c:if test="${not empty sessionScope.about}">
    								<c:out value="${sessionScope.about}" />
    							</c:if></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
          </div>
        </div>
      </div>
    </div><!--End Timeline content -->

    
<% } else { response.sendRedirect("./login.jsp"); } %>
  </body>
</html>
