package contoller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.InvalidParameterException;
import model.IPersonDAO;
import model.Person;
import model.PersonDAO;

/**
 * Servlet implementation class RegisterUserServlet
 */
@WebServlet("/register")
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstName = request.getParameter("first_name");
		System.out.println(firstName);
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String password = request.getParameter("password");

		boolean nullValue = firstName == null || lastName == null || email == null || gender == null
				|| password == null;

		if(nullValue) {
			return;
		}

		try {

			String isMale = genderValidate(gender);

			IPersonDAO dao = new PersonDAO();

			Person person = new Person(firstName, lastName, email, isMale, password);

			dao.addPerson(person);

			HttpSession session = request.getSession();
			session.setAttribute("user", person);

			response.sendRedirect("./login.jsp");

		} catch (InvalidParameterException e) {

			RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");

			request.setAttribute("invalid", "Password must contains at least "
					+ "8 characters, 1 digit, 1 uppercase letter "
					+ "and at least 2 lowercase letters");

			rd.forward(request, response);
		}
	}

	private String genderValidate(String gender) {
		return gender.equalsIgnoreCase("male") ? "male" : "female";
	}

}
