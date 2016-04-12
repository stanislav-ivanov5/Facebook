package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.IPostingDAO;
import model.Post;
import model.PostingDAO;

/**
 * Servlet implementation class GetAllPostServlet
 */
@WebServlet("/getAllPosts2")
public class GetAllPostServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		IPostingDAO dao = new PostingDAO();
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String friendEmail = (String) session.getAttribute("friend_email");
		
		if (friendEmail != null) {
			email = friendEmail;
		}
		
		List<Post> allPosts = dao.getAllPosts(email, true);

		Gson gsonObj = new Gson();
		String json = gsonObj.toJson(allPosts);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		out.print(json);
		out.flush();

	}
}
