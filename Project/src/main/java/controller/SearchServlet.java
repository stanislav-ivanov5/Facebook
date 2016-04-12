package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SearchDAO;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession(false) == null) {
			response.sendRedirect("./login.jsp");
			return;
			
		} else {
			// Get user's input
			String input = request.getParameter("search");

			System.out.println("[SearchServlet] --- [input]=" + input);

			// Invokes dao and proceeds search query
			SearchDAO dao = new SearchDAO();
			Map<String, Map<String, String>> map = dao.searchByName(input);

			// Put results in the session
			HttpSession session = request.getSession();
			session.setAttribute("searchResults", map);

			// Print results to console
			for (Entry<String, Map<String, String>> e : map.entrySet()) {
				System.out.println("[key]-" + e.getKey());
				for (String key2 : e.getValue().keySet()) {
					System.out.println("-[" + key2 + "]-" + e.getValue().get(key2));
				}
			}

			response.sendRedirect("./view/search.jsp");
		}
	}
}
