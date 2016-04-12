package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.FriendDAO;
import model.Person;

/**
 * Servlet implementation class FriendsDisplayServlet
 */
@WebServlet("/displayFriends")
public class FriendsDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FriendDAO dao= new FriendDAO();
		HttpSession session= request.getSession();
		String email=(String) session.getAttribute("email");
		List<Person>listFriends= dao.getAllFriends(email);
		System.out.println("list of friends is: ");
		for (Person person : listFriends) {
			System.out.println(person);
		}
		session.setAttribute("friends", listFriends);
		
		response.sendRedirect("./view/friends.jsp");
	}

}
