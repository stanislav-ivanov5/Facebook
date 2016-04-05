package contoller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PersonDAO;

/**
 * Servlet implementation class EditPersonServlet
 */
@WebServlet("/EditPerson")
public class EditPersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PersonDAO dao = new PersonDAO();
		
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String location = request.getParameter("location");
		String phone = request.getParameter("phone");
		String about = request.getParameter("about");
		
		System.out.println("[EditPersonServlet] " + firstName + " " + lastName + " " + location + " " + about);
		
		// TODO UPDATE locations TABLE IN DB
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		
		System.out.println("[EditPersonServlet] ---- email ---" + email);
		
		dao.updatePersonInfo(email, firstName, lastName, location, phone, about);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("./GetPersonInfo");
		rd.forward(request, response);
	}
	
	
	

}
