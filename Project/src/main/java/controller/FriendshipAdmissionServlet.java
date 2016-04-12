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

import model.FriendDAO;
import model.Person;

/**
 * Servlet implementation class AcceptDeclineFriendhipservlet
 */
@WebServlet("/acceptDecline")
public class FriendshipAdmissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String decision= request.getParameter("decision");
		String emailSender= request.getParameter("senderEmail");
		HttpSession session = request.getSession();
		FriendDAO dao = new FriendDAO();
		if(decision.equals("false")){
			 dao = new FriendDAO();
			dao.removePendingRequestFromPerson(emailSender);
			
			session.setAttribute("deletedRequest", "The request was successfully deleted.");			
			response.sendRedirect("./notifications");
			session.setAttribute("hasNotifications", null);
		}
		else{
			String emailRequestAdmitter= (String) session.getAttribute("email");
			dao.addPersonToFriendsList(emailSender,emailRequestAdmitter);
			dao.removePendingRequestFromPerson(emailSender);
			session.setAttribute("acceptRequest", "You have one new friend.");
			session.setAttribute("hasNotifications", null);
			response.sendRedirect("./notifications");
		//	List<Person> friendsList=dao.getAllFriends(emailRequestAdmitter );
		}
	}

}
