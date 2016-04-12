package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.InvalidParameterException;
import model.Person;
import model.PersonDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		
		try {

			PersonDAO dao = new PersonDAO();
			Person person = dao.personLoginCheck(email, password);
			
			if (person != null) {
				
				/*
				 * put email in the session as an attribute
				 */
				HttpSession session = request.getSession();
				session.setAttribute("email", email);
				
				//--------------------------------------------------------
				System.out.println("[LoginServlet --- doPost] --- Login email = " + 
						session.getAttribute("email"));

				ResultSet rs = dao.createPersonFromDB((String) session.getAttribute("email"));
				while(rs.next()){
					session.setAttribute("first_name", rs.getString("first_name"));
					session.setAttribute("last_name", rs.getString("last_name"));
				}
				
				response.sendRedirect("./GetPersonInfo");
				
				/*
				 *  Person object is not created
				 */
			} else {
				System.out.println("[LoginServlet] [person]= null");
				
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
				
				request.setAttribute("invalid", "Invalid user email or password.");
				rd.forward(request, response);
				
			}
		} catch (InvalidParameterException e) {
			System.out.println("[LoginServlet] --- doPost() --- exception=" + e.getMessage());			
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			request.setAttribute("invalid", "Invalid login.");
			rd.forward(request, response);
			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

