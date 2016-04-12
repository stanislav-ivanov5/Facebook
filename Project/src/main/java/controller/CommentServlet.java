package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Comment;
import model.CommentDAO;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String commentText = request.getParameter("comment");
		String postIdString = request.getParameter("idOfPost");

		CommentDAO dao = new CommentDAO();
		HttpSession session = request.getSession();

		String email = (String) session.getAttribute("email");
		String friendEmail = (String) session.getAttribute("friend_email");
		Integer postId = Integer.parseInt(postIdString);
		
		
		
		/*
		 *  Update db with new comment
		 */
		if (commentText != null && postId != null && email != null) {
			dao.putCommentOnPost(postId, email, commentText);
		}

		if(friendEmail == null) {
			response.sendRedirect("./getAllPosts");
		} else {
			response.sendRedirect("./getAllPosts?friend_email=" + friendEmail);
		}
	}

}
