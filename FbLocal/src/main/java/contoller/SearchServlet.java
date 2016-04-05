package contoller;

import java.io.IOException;
import java.util.ArrayList;
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
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get user's input
		String input = request.getParameter("search");
		
		System.out.println("[SearchServlet] --- [input]=" + input);
		
		// Invokes dao and proceeds search query
		SearchDAO dao = new SearchDAO();
		Map<String, List<String>> map = dao.searchByName(input);
		
		// Put results in the session
		HttpSession session = request.getSession();
		session.setAttribute("searchResults", map);
		
		
		for(Entry<String, List<String>> e : map.entrySet()) {
			System.out.println("[key]-" + e.getKey());
			for(String value : e.getValue()) {
				System.out.println("-[value]-" + value);
			}
		}
	}
}
