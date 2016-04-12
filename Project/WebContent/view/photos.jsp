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
      <h1 class="profile-name"><c:out value="${sessionScope.first_name}${' '}${sessionScope.last_name}" /></h1>
    </div>

   <c:import url="./left_profile_bar.jsp"></c:import>

          <div class="profile-info col-md-8">
            <!-- panel photos -->
            <div class="panel panel-info panel-list-photos">
              <div class="panel-heading">
              		<div class="upload-photo">
              		<c:if test="${empty sessionScope.friend_email}">
               				 <form action="../pictureUpload" method="post" enctype="multipart/form-data">
								<div class="form-group">							
											<input type="file" name="picture"  />		
									<input type="submit" class="btn btn-info" value="Upload">
								</div>
								</form></c:if>
					</div>
              </div>
              <div class="panel-body">
                <div class="row">
                  
                  	<c:forEach items="${sessionScope.pictures}" var="value">
                 		<div class="col-md-4 col-sm-6 col-xs-6 photo-content">
                  			 <img src="pictures/<c:out value="${value}"></c:out>" alt="photo 9" class="img-responsive  show-in-modal">
                   		</div>  
                  	</c:forEach>   
                </div>
              </div>
              <div class="panel-footer">

              </div> 
            </div><!-- end panel photos -->
          </div>
      </div>
      </div>
      </div>
    </div><!--End Timeline content -->

   
    </div><!-- Online users sidebar content-->
    <% } else { response.sendRedirect("../login.jsp"); } %>
  </body>
</html>
