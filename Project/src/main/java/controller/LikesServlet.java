package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PostingDAO;

/**
 * Servlet implementation class LikesServlet
 */
@WebServlet("/likes")
public class LikesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		String postIdFromForm= request.getParameter("likesPostid");
		int postId= Integer.parseInt(postIdFromForm);
		PostingDAO dao= new PostingDAO();
		dao.incrementPostLikes(postId);
		
		String email = (String) session.getAttribute("email");
		String friendEmail = (String) session.getAttribute("friend_email");
				
		if (friendEmail != null) {
			response.sendRedirect("./getAllPosts?friend_email=" + friendEmail);			
		}
		else {
			response.sendRedirect("./getAllPosts");
		}
	}

}
