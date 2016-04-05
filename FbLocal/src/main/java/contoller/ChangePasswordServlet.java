package contoller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PersonDAO;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/change-password")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currentPass = request.getParameter("currentPassword");
		String newPass = request.getParameter("newPassword");

		// check if change password button is hit with no parameters.
		if (currentPass == null && newPass == null) {
			RequestDispatcher dispacher = request.getRequestDispatcher("./view/edit-profile.jsp");
			dispacher.forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		PersonDAO dao = new PersonDAO();
		boolean isValid = dao.retrieveCurrentPassword(email, currentPass);

		if (isValid == false) {
			session.setAttribute("invalidPassword", "Invalid Current Password");
		}

		else {
			boolean isNewPasswordvalid = dao.passwordValidate(newPass);
			if (isNewPasswordvalid && isValid) {
				dao.updatePassword(newPass, email);
				session.setAttribute("validPassword", "Your password is updated!");
			}
		}
		response.sendRedirect("./view/edit-profile.jsp");
	}
}
