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
 * Servlet implementation class NotificationsServlet
 */
@WebServlet("/notifications")
public class NotificationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		String email= (String) session.getAttribute("email");
		
		FriendDAO dao= new FriendDAO();
		List<Person> listPeople=dao.checkForNotification(email);
		
		session.setAttribute("listPeople", listPeople);
		response.sendRedirect("./view/notifications.jsp");
		
		
		
	}

}
