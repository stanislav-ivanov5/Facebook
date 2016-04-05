<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top navbar-principal">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
         </button>
			<a class="navbar-brand" href="index.html">
				<b>Ittbook</b>
			</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<div class="col-md-5 col-sm-3">
				<form class="navbar-form">
					<div class="form-group" style="display: inline;">
						<div class="input-group" style="display: table;">
							<input class="form-control" name="search" placeholder="Search..."
								autocomplete="off" type="text"> <span
								class="input-group-addon" style="width: 1%;"> <span
								class="glyphicon glyphicon-search"></span>
							</span>
						</div>
					</div>
				</form>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="./profile.jsp"> <strong>
							<c:out value="${sessionScope.first_name}" />
					</strong> <c:if test="${empty sessionScope.profilePic}">
							<img src="./img/user-default-pic.png" class="img-nav">
						</c:if> <c:if test="${not empty sessionScope.profilePic}">
							<img src="img/<c:out value="${sessionScope.profilePic}"></c:out>"
								class="img-nav">
						</c:if>
				</a></li>
				<li><a href="home.html"><i class="fa fa-bars"></i>&nbsp;Home</a></li>
				<li><a href="messages.html"><i class="fa fa-comments"></i></a></li>
				<li><a href="notifications.html"><i class="fa fa-globe"></i></a></li>
				<li><a href="search.html"><i class="fa fa-users"></i></a></li>
				<li><a href="../about.jsp"><i class="fa fa-info-circle"></i></a></li>
				<li><a href="#" class="nav-controller"><i class="fa fa-user"></i></a></li>
				<li><a href="../logout"><i class="fa fa-bars"></i><strong>&nbsp;Logout</strong></a></li>
			</ul>
	</div>
	</div>
	</nav>

	<!-- Timeline container -->
	<div class="container" style="margin-top: 66px;">
		<div class="row row-broken">
			<div class="col-md-10 cover">
				<img src="./img/Cover/nature.jpg" class="cover-img" alt="image">
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
			</div>
		</div>
		<hr>
		<div class="row">
	    	<div class="col-md-10">
	    		<div class="row">
		    		<!-- left -->
		    		<div class="col-md-6">
	    				<div class="row">
		    				<div class="col-md-12">
						        <div class="panel profile-info">
						          <form>
						              <textarea class="form-control input-lg p-text-area" rows="2"
											placeholder="Whats in your mind today?">
										</textarea>
						          </form>
						          <div class="panel-footer">
						              <button type="button" class="btn btn-info pull-right">Post</button>
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
								<div class="box box-widget">
									<div class="box-header with-border">
									  <div class="user-block">
									    <img class="img-circle" src="img/user-default-pic.png" alt="User Image">
									    <span class="username"><a href="#">Katya Angintiew</a></span>
									    <span class="description">Shared publicly - 7:30 PM Today</span>
									  </div>
									  <div class="box-tools">
									  <button type="button" class="btn btn-box-tool" data-toggle="tooltip" title="" data-original-title="Mark as read">
									    <i class="fa fa-circle-o"></i></button>
									    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
									    <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
									  </div>
									</div>
									<div class="box-body">
									  <p>Far far away, behind the word mountains, far from the
											countries Vokalia and Consonantia, there live the blind
											texts. Separated they live in Bookmarksgrove right at</p>

										<p>the coast of the Semantics, a large language ocean. A
											small river named Duden flows by their place and supplies it
											with the necessary regelialia. It is a paradisematic country,
											in which roasted parts of sentences fly into your mouth.</p>


									  <div class="attachment-block clearfix">
									    <img class="attachment-img" src="img/Photos/3.jpg" alt="Attachment Image">
									    <div class="attachment-pushed">
									    <h4 class="attachment-heading"><a href="#">Lorem ipsum text generator</a></h4>
									    <div class="attachment-text">
									    Description about the attachment can be placed here.
									    Lorem Ipsum is simply dummy text of the printing and typesetting industry... <a href="#">more</a>
									    </div>
									    </div>
									  </div>
									  <button type="button" class="btn btn-default btn-xs"><i class="fa fa-share"></i> Share</button>
									  <button type="button" class="btn btn-default btn-xs"><i class="fa fa-thumbs-o-up"></i> Like</button>
									  <span class="pull-right text-muted">45 likes - 2 comments</span>
									</div>
									<div class="box-footer box-comments">
									  <div class="box-comment">
									    <img class="img-circle img-sm" src="img/Friends/woman-2.jpg" alt="User Image">
									    <div class="comment-text">
									      <span class="username">
									      Maria Gonzales
									      <span class="text-muted pull-right">8:03 PM Today</span>
									      </span>
									      It is a long established fact that a reader will be distracted
									      by the readable content of a page when looking at its layout.
									    </div>
									  </div>
									  <div class="box-comment">
									    <img class="img-circle img-sm" src="img/Friends/woman-3.jpg" alt="User Image">
									    <div class="comment-text">
									      <span class="username">
									      Nora Havisham
									      <span class="text-muted pull-right">8:03 PM Today</span>
									      </span>
									      The point of using Lorem Ipsum is that it has a more-or-less
									      normal distribution of letters, as opposed to using
									      'Content here, content here', making it look like readable English.
									    </div>
									  </div>
									</div>
									<div class="box-footer">
									  <form action="#" method="post">
									    <img class="img-responsive img-circle img-sm" src="img/Friends/woman-4.jpg" alt="Alt Text">
									    <div class="img-push">
									      <input type="text" class="form-control input-sm" placeholder="Press enter to post comment">
									    </div>
									  </form>
									</div>
								</div>
					        </div><!-- end post-->
		    				<!-- post -->
		    				<div class="col-md-12">
								<div class="box box-widget">
									<div class="box-header with-border">
									  <div class="user-block">
									    <img class="img-circle" src="img/Friends/woman-4.jpg" alt="User Image">
									    <span class="username"><a href="#">Katya Angintiew</a></span>
									    <span class="description">Shared publicly - 7:30 PM Today</span>
									  </div>
									  <div class="box-tools">
									  <button type="button" class="btn btn-box-tool" data-toggle="tooltip" title="" data-original-title="Mark as read">
									    <i class="fa fa-circle-o"></i></button>
									    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
									    <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
									  </div>
									</div>
									<div class="box-body">
									  <p>Far far away, behind the word mountains, far from the
									  countries Vokalia and Consonantia, there live the blind
									  texts. Separated they live in Bookmarksgrove right at</p>

									  <p>the coast of the Semantics, a large language ocean.
									  A small river named Duden flows by their place and supplies
									  it with the necessary regelialia. It is a paradisematic
									  country, in which roasted parts of sentences fly into
									  your mouth.</p>
									  <button type="button" class="btn btn-default btn-xs"><i class="fa fa-share"></i> Share</button>
									  <button type="button" class="btn btn-default btn-xs"><i class="fa fa-thumbs-o-up"></i> Like</button>
									  <span class="pull-right text-muted">45 likes - 2 comments</span>
									</div>
									<div class="box-footer box-comments">
									  <div class="box-comment">
									    <img class="img-circle img-sm" src="img/Friends/woman-2.jpg" alt="User Image">
									    <div class="comment-text">
									      <span class="username">
									      Maria Gonzales
									      <span class="text-muted pull-right">8:03 PM Today</span>
									      </span>
									      It is a long established fact that a reader will be distracted
									      by the readable content of a page when looking at its layout.
									    </div>
									  </div>
									  <div class="box-comment">
									    <img class="img-circle img-sm" src="img/Friends/woman-3.jpg" alt="User Image">
									    <div class="comment-text">
									      <span class="username">
									      Nora Havisham
									      <span class="text-muted pull-right">8:03 PM Today</span>
									      </span>
									      The point of using Lorem Ipsum is that it has a more-or-less
									      normal distribution of letters, as opposed to using
									      'Content here, content here', making it look like readable English.
									    </div>
									  </div>
									</div>
									<div class="box-footer">
									  <form action="#" method="post">
									    <img class="img-responsive img-circle img-sm" src="img/Friends/woman-4.jpg" alt="Alt Text">
									    <div class="img-push">
									      <input type="text" class="form-control input-sm" placeholder="Press enter to post comment">
									    </div>
									  </form>
									</div>
								</div>
					        </div><!-- end post-->
					   </div>
				    </div><!-- end left -->


				    <!-- right -->
				    <div class="col-md-6">
				    	<div class="row">
				    		<div class="col-md-12"> 
								<div class="box box-widget">
									<div class="box-header with-border">
									  <div class="user-block">
									    <img class="img-circle" src="img/Friends/woman-4.jpg" alt="User Image">
									    <span class="username"><a href="#">Katya Angintiew</a></span>
									    <span class="description">Shared publicly - 7:30 PM Today</span>
									  </div>
									  <div class="box-tools">
									    <button type="button" class="btn btn-box-tool" data-toggle="tooltip" title="" data-original-title="Mark as read">
									    <i class="fa fa-circle-o"></i></button>
									    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
									    </button>
									    <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
									  </div>
									</div>

									<div class="box-body" style="display: block;">
									  <img class="img-responsive pad" src="img/Photos/5.jpg" alt="Photo">
									  <p>I took this photo this morning. What do you guys think?</p>
									  <button type="button" class="btn btn-default btn-xs"><i class="fa fa-share"></i> Share</button>
									  <button type="button" class="btn btn-default btn-xs"><i class="fa fa-thumbs-o-up"></i> Like</button>
									  <span class="pull-right text-muted">127 likes - 3 comments</span>
									</div>
									<div class="box-footer box-comments" style="display: block;">
									  <div class="box-comment">
									    <img class="img-circle img-sm" src="img/Friends/woman-6.jpg" alt="User Image">
									    <div class="comment-text">
									      <span class="username">
									      Maria Gonzales
									      <span class="text-muted pull-right">8:03 PM Today</span>
									      </span>
									      It is a long established fact that a reader will be distracted
									      by the readable content of a page when looking at its layout.
									    </div>
									  </div>

									  <div class="box-comment">
									    <img class="img-circle img-sm" src="img/Friends/woman-9.jpg" alt="User Image">
									    <div class="comment-text">
									      <span class="username">
									      Luna Stark
									      <span class="text-muted pull-right">8:03 PM Today</span>
									      </span>
									      It is a long established fact that a reader will be distracted
									      by the readable content of a page when looking at its layout.
									    </div>
									  </div>
									</div>
									<div class="box-footer" style="display: block;">
									  <form action="#" method="post">
									    <img class="img-responsive img-circle img-sm" src="img/Friends/woman-4.jpg" alt="Alt Text">
									    <div class="img-push">
									      <input type="text" class="form-control input-sm" placeholder="Press enter to post comment">
									    </div>
									  </form>
									</div>
								</div>
							</div>
				    		<div class="col-md-12"> 
								<div class="box box-widget">
									<div class="box-header with-border">
									  <div class="user-block">
									    <img class="img-circle" src="img/Friends/woman-4.jpg" alt="User Image">
									    <span class="username"><a href="#">Katya Angintiew</a></span>
									    <span class="description">Shared publicly - 7:30 PM Today</span>
									  </div>
									  <div class="box-tools">
									    <button type="button" class="btn btn-box-tool" data-toggle="tooltip" title="" data-original-title="Mark as read">
									    <i class="fa fa-circle-o"></i></button>
									    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
									    </button>
									    <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
									  </div>
									</div>

									<div class="box-body" style="display: block;">
									  <img class="img-responsive pad" src="img/Photos/9.jpg" alt="Photo">
									  <p>I took this photo this morning. What do you guys think?</p>
									  <button type="button" class="btn btn-default btn-xs"><i class="fa fa-share"></i> Share</button>
									  <button type="button" class="btn btn-default btn-xs"><i class="fa fa-thumbs-o-up"></i> Like</button>
									  <span class="pull-right text-muted">127 likes - 3 comments</span>
									</div>
									<div class="box-footer box-comments" style="display: block;">
									  <div class="box-comment">
									    <img class="img-circle img-sm" src="img/Friends/woman-5.jpg" alt="User Image">
									    <div class="comment-text">
									      <span class="username">
									      Maria Gonzales
									      <span class="text-muted pull-right">8:03 PM Today</span>
									      </span>
									      It is a long established fact that a reader will be distracted
									      by the readable content of a page when looking at its layout.
									    </div>
									  </div>

									  <div class="box-comment">
									    <img class="img-circle img-sm" src="img/Friends/woman-6.jpg" alt="User Image">
									    <div class="comment-text">
									      <span class="username">
									      Luna Stark
									      <span class="text-muted pull-right">8:03 PM Today</span>
									      </span>
									      It is a long established fact that a reader will be distracted
									      by the readable content of a page when looking at its layout.
									    </div>
									  </div>
									</div>
									<div class="box-footer" style="display: block;">
									  <form action="#" method="post">
									    <img class="img-responsive img-circle img-sm" src="img/Friends/woman-4.jpg" alt="Alt Text">
									    <div class="img-push">
									      <input type="text" class="form-control input-sm" placeholder="Press enter to post comment">
									    </div>
									  </form>
									</div>
								</div>
							</div>
				    	</div>
				    </div><!-- end right -->
	    		</div>
			</div>
		</div>
    </div><!-- end timeline content-->
    <!-- Online users sidebar content-->
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
    
    <footer class="welcome-footer">
      <div class="container">
        <p>
          <div class="footer-links">
            <a href="#">Terms of Use</a> | 
            <a href="#">Privacy Policy</a> | 
            <a href="#">Developers</a> | 
            <a href="#">Contact</a> | 
            <a href="#">About</a>
          </div>   
          Copyright &copy; Company - All rights reserved       
        </p>
      </div>
    </footer>
  </body>
</html>
