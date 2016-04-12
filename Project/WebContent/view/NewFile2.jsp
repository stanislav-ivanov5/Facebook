<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script>
		$(document).ready(
				function() {
					var form = $('form');
					var submit = $('#submit');

					form.on('submit', function(comment, idOfPost) {
						// prevent default action
						e.preventDefault();
						// send ajax request
						$.ajax({
							url : '../TESTComment',
							type : 'POST',
							cache : false,

							data : jQuery.param({
								'comment' : comment,
							}),

							beforeSend : function() {
								// change submit button value text and disabled it
								submit.val('Submitting...').attr('disabled',
										'disabled');
							},
							success : function(data) {
								// Append with fadeIn see http://stackoverflow.com/a/978731
								var item = $(data).hide().fadeIn(800);
								$('.comment-block').append(item);

								// reset form and button
								form.trigger('reset');
								submit.val('Submit Comment').removeAttr(
										'disabled');
							},
							error : function(e) {
								alert(e);
							}
						});
					});
				});
	</script>

	

	<!-- comment form -->
	<form id="form" method="post">
		<!-- need to supply post id with hidden fild -->
		<input type="hidden" name="idOfPost" value="1"> <label>
			<span>Your comment *</span></br> <textarea name="comment" id="comment"
				cols="30" rows="10" placeholder="Type your comment here...."
				required>
      </textarea>
		</label><br /> <input type="submit" id="submit" value="Submit Comment">
	</form>
	
	
	<!-- sending response with new comment and html markup-->
	<div class="comment-item">
		<div class="comment-post">
			<h3>
				<c:out value="Georgi Dimitrov" />
				<span>said....</span>
			</h3>
			<p>
				<c:out value="Text" />

			</p>
		</div>
	</div>

	<div class="post">
		<!-- post will be placed here from db -->
	</div>
	<div class="comment-block">
		<!-- comment will be apped here from db-->
	</div>
	
</body>
</html>