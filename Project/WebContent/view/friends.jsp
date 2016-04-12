<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ITTBOOK</title>
</head>
<body>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<% if (session.getAttribute("email") != null) {  %>
	<%@include file="./nav_bar.jsp" %>
	<div class="row text-center color-container">
      <h1 class="profile-name"><c:out value="${sessionScope.first_name}${' '}${sessionScope.last_name}" /></h1>
    </div>

	<%@include file="./left_profile_bar.jsp" %>
			
			
			<div class="profile-info col-md-8">
			<c:if test="${not empty sessionScope.friends}">
			<c:forEach items="${sessionScope.friends}" var="friend">
              <div class="col-md-5">
                  <div class="widget-head-color-box lazur-bg p-lg text-center">
                      <div class="user-heading round">
                          <h4><c:out value="${friend.firstName}${' '}${friend.lastName}"></c:out></h4>
                      </div>
                      <a href="../getAllPosts?friend_email=${friend.email}">
                      <c:if test="${not empty friend.profilePicPath}">
                      	   <img src="./img/<c:out value="${friend.profilePicPath}"></c:out>" 
                      	   class="img-circle circle-border m-b-md" alt="profile">
                      </c:if>
                      <c:if test="${ empty friend.profilePicPath}">
                      <img src="./img/user-default-pic.png" class="img-circle circle-border m-b-md" alt="profile">
                      <div></c:if> </a>                        
              		</div>
         	 	</div>
         	</c:forEach></c:if>
         	<c:if test="${empty sessionScope.friends}">
          		<h3 style="color:#39bbdb"> &nbsp;&nbsp;&nbsp;&nbsp;The list of friends is empty. Find some.</h3>
          </c:if>
         	 </div>
            
          
          
        </div>
      </div>
      </div>
    </div><!--End Timeline content -->
    
 <% } else { response.sendRedirect("../login.jsp"); } %>
    
</body>
</html>