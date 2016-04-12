<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
    <!--  End fixed navbar -->

	
	<!-- Timeline content -->
	<div class="row">
		<div class="container">
		<div class="row row-broken">
		<c:if test="${empty sessionScope.friend_email }">
			<div class="col-md-10 cover">
		
			<c:if test="${empty sessionScope.coverPic }">
				<img  class="cover-img" src="./img/Cover/nature.jpg"  alt="image">
			</c:if>
			<c:if test="${not empty sessionScope.coverPic }">
				
				<img class="cover-img" src="./coverPhotos/<c:out value="${sessionScope.coverPic}"></c:out>" alt="image">
			</c:if>
				<div class="cover-inside cover-blackout text-c text-light">
							<c:if test="${empty sessionScope.profilePic}" >
                          		<img class="cover-avatar size-md img-round" src="./img/user-default-pic.png" alt="profile" />
                        	</c:if>
							<c:if test="${not empty sessionScope.profilePic}">
                    	    	<img class="cover-avatar size-md img-round" src="img/<c:out value="${sessionScope.profilePic}"></c:out>" alt="profile" >
                	        </c:if>
						<div class="field name font-weight-700">
							<c:out
								value="${sessionScope.first_name}${' '}${sessionScope.last_name}" />
						</div>
					<br />
					<div class="field place">
						<c:if test="${not empty sessionScope.location}">
							<c:out value="${sessionScope.location}" />
						</c:if>
					</div>
				</div>
			</div></c:if><br><br>
		</div>
			<div class="row">
				<div class="col-md-10 no-paddin-xs">

					
					<c:import url="./left_profile_bar.jsp"></c:import>
					<!-- end left content-->
					<!-- right  content-->
			



<div class="profile-info col-md-8">
						<div class="row">
							<div class="col-md-12">
								<div class="panel profile-info">
									<form action="../posting" method="post">
										<textarea class="form-control input-lg p-text-area" rows="2"
											placeholder="Whats in your mind today?" name="posting"></textarea>
									<!--	<input type="hidden" name="email" value="${ sessionScope.email }" />-->
										
										<div class="panel-footer">
										<input type="submit" class="btn btn-info pull-right" value="Post" />
									</form>
									<ul class="nav nav-pills">
										<li><a href="#"><i class="fa fa-map-marker"></i></a></li>
										<li><a href="#"><i class="fa fa-camera"></i></a></li>
										<li><a href="#"><i class="fa fa-film"></i></a></li>
										<li><a href="#"><i class="fa fa-microphone"></i></a></li>
									</ul>

								</div>
							</div>
						</div>
						<!-- post -->
						<div class="col-md-12">
							<c:if test="${not empty sessionScope.posts}">
								<c:forEach items="${sessionScope.posts}" var="post">
								 
									<div class="box box-widget"> 
										<div class="box-header with-border">
											<div class="user-block">
												<span class="username"><a href="#">
												
												<c:out value="${post.firstName}${' '}${post.lastName}"></c:out></a></span>
												<span class="description">Shared publicly - 
												
												<c:out value="${post.date}"></c:out></span>
											</div>
											<div class="box-tools">
												<button type="button" class="btn btn-box-tool" data-toggle="tooltip" title="" data-original-title="Mark as read">
													<i class="fa fa-circle-o"></i>
												</button>
												<button type="button" class="btn btn-box-tool" data-widget="collapse">
													<i class="fa fa-minus"></i>
												</button>
												<button type="button" class="btn btn-box-tool" data-widget="remove">
													<i class="fa fa-times"></i>
												</button>
											</div>
										</div>
										<div class="box-body">
											<p>
												<c:out value="${post.text}"></c:out>
											</p>
											<br />
											<form action="../likes" method="get">
											<input type="hidden" name="likesPostid" value="${post.postId}"/>
												<input type="submit" value="Like" name="likeButton" class="btn btn-default btn-xs" />
												
													<i class="fa fa-thumbs-o-up"></i> 
													
													<span class="pull-right text-muted">
												 
													<c:out value="${post.countLikes}" /> likes </span>
											</form>
										</div>

										
										<c:if test="${not empty post.allComments}">
											
											<c:forEach items="${post.allComments}" var="eachComment">
											
												<div class="box-footer box-comments" comment-id="${ eachComment.commentId }" post-id="${ post.postId }">
													<div class="box-comment">
														<div class="comment-text">
															<span class="username"> </span>
															<b><c:out value="${ eachComment.firstName }"></c:out>:</b>
															<c:out value="${eachComment.text}"></c:out>
															
														</div>
													</div>
												</div>
												<c:set var="aComment" value="${ eachComment }" />
										
											</c:forEach>
											</c:if>
											
										<div class="box-footer after-last-comment" post-id="${ post.postId }" >
											
											<form action="../comment" method="post" id="postingForm">
												<div class="img-push">
													<input type="text" class="form-control input-sm" placeholder="Press enter to post comment" name="comment">
													<input type="hidden" name="email" value="${ sessionScope.email }" />
													<c:set var="postId" value="${aComment.matchingPostId}" scope="page" />
													<input id="${aComment.matchingPostId}" type="hidden" name="idOfPost" value="${post.postId}">
													
												<!--	<button onclick="addcomment({${aComment.matchingPostId}, ${aComment}}"
														type="submit" value="Submit" class="btn">Submit
														</button>
												 -->
												</div>
											</form>
										</div>
									</div>
								</c:forEach>
							</c:if>

						</div>
						<!-- end post-->
						<!-- end post-->
					</div>

				</div>
				<!--end right  content-->
			</div>
		</div>
	</div>
	</div>
	</div>
	<!-- end timeline content-->
	<script type="text/javascript">
    $(document).ready(
    			function() {
        	function onSuccess(data) {
       		
        		console.log(new Date());
            	
				for(var i = 0; i < data.length; i++) {
					var post = data[i];
					var post_id = post.postId;

					for(var j = 0; j < post.allComments.length; j++) {
						var comment = post.allComments[j];
						var comment_id = comment.commentId;

						var searchStr = "div[post-id='" + post_id + "'][comment-id='" + comment_id + "']" ;

						var element = $(searchStr);

						if(element.length != 0) {
							continue;
						}

						var html = "<div class=\"box-footer box-comments\"" 
										+ " comment-id=\"" + comment_id + "\" post-id=\"" + post_id + "\">" 
											+ "<div class=\"box-comment\">" 
											+ "<div class=\"comment-text\" style=\"display: flex\">" 
												+ "<span class=\"username\">" + comment.firstName + ":</span>" + comment.text + "</div>"
									+ "</div>"
									+ "</div>";

						var footer_element = $("div.after-last-comment[post-id='" + post_id + "']");

						$(html).insertBefore(footer_element);

					} 
				}
            };

            
            
    		function invokeAjax() {
				$.ajax({
					url : '../getAllPosts2',
					type : 'POST',
					cache : false,

					success : onSuccess,

				});
			};

			setInterval(invokeAjax, 5000);
        }	
    );
    </script>

<% } else { response.sendRedirect("../login.jsp"); } %>
</body>
</html>
