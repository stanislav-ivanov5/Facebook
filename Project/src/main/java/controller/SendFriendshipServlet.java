package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.InvalidParameterException;
import model.FriendDAO;

@WebServlet("/friendshipRequest")
public class SendFriendshipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String emailOfPersonToSendRequestTo = request.getParameter("personToSendRequestTo");
		System.out.println("email of the person to send request to is: " + emailOfPersonToSendRequestTo);
		HttpSession session = request.getSession();
		String emailOfTheSenderOfTheRequest = (String) session.getAttribute("email");
		
		FriendDAO dao = new FriendDAO();

		if (emailOfPersonToSendRequestTo != null && emailOfTheSenderOfTheRequest != null) {
			try {
				boolean doesSuchRequestExist=dao.checkIfSuchRequestExists(emailOfTheSenderOfTheRequest, emailOfPersonToSendRequestTo);
				
				if(doesSuchRequestExist==false){
					dao.addPendingRequest(emailOfPersonToSendRequestTo, emailOfTheSenderOfTheRequest);
					response.sendRedirect("./view/home.jsp");
				}
				else{
					session.setAttribute("requestRepetition", "Such request already exists.");
					response.sendRedirect("./view/search.jsp");
					
					
				}
			} catch (InvalidParameterException e) {
				e.printStackTrace();
			}											
		}
		

	}

}
