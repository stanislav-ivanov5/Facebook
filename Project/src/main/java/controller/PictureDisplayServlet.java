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

import model.PictureDAO;

/**
 * Servlet implementation class PictureDisplayServlet
 */
@WebServlet("/pictureDisplay")
public class PictureDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PictureDAO dao = new PictureDAO();
		HttpSession session= request.getSession();
		
		String email= (String)session.getAttribute("email");
		String friendEmail= (String)session.getAttribute("friend_email");
		if(friendEmail!=null){
			List<String> list= dao.getAllPictures(friendEmail);
			session.setAttribute("pictures", list);	
			response.sendRedirect("./view/photos.jsp");
		}
		else{
		List<String> list= dao.getAllPictures(email);
		session.setAttribute("pictures", list);	
		response.sendRedirect("./view/photos.jsp");
		}
	}

}
