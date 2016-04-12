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

import exceptions.InvalidParameterException;
import model.IPersonDAO;
import model.IPostingDAO;
import model.Person;
import model.PersonDAO;
import model.Post;
import model.Post;
import model.PostingDAO;

/**
 * Servlet implementation class GetAllPostServlet
 */
@WebServlet("/getAllPosts")
public class GetAllPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String friendEmail = request.getParameter("friend_email");

		if (friendEmail == null) {
			doPost(request, response);
			return;
		}
		HttpSession session = request.getSession();
		IPostingDAO dao = new PostingDAO();
		List<Post> allPosts = dao.getAllPosts(friendEmail, false);
		
		System.out.println("Those are the posts:");
		for (Post post : allPosts) {
			System.out.println(post.toString());
		}
		session.setAttribute("posts", allPosts);
		PersonDAO personDao = new PersonDAO();
		Person person = personDao.getPerson(friendEmail);
		session.setAttribute("home_first_name", person.getFirstName());
		session.setAttribute("home_last_name", person.getLastName());
		session.setAttribute("home_profile_pic", person.getProfilePicPath());
		System.out.println("getAllPost profile pic name na frienda:"+session.getAttribute("home_profile_pic"));
		session.setAttribute("home_email", friendEmail);
		
		session.setAttribute("friend_email", friendEmail);
		System.out.println("friend meila: "+session.getAttribute("friend_email"));
		response.sendRedirect("./view/home.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		IPostingDAO dao = new PostingDAO();
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		if (email == null) {
			return;
		}

		List<Post> allPosts = dao.getAllPosts(email, true);

		session.setAttribute("posts", allPosts);
		session.setAttribute("home_first_name", session.getAttribute("first_name"));
		session.setAttribute("home_last_name", session.getAttribute("last_name"));
		session.setAttribute("home_profile_pic", session.getAttribute("profilePic"));
		System.out.println("getAllPost profile pic name:"+session.getAttribute("home_profile_pic"));
		
		session.setAttribute("home_email", email);
		session.setAttribute("friend_email", null);
			
		response.sendRedirect("./view/home.jsp");

	}

}
