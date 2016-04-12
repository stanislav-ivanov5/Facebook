package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.FriendDAO;
import model.Person;
import model.PersonDAO;

/**
 * Servlet implementation class GetPersonInfoServlet
 */
@WebServlet("/GetPersonInfo")
public class GetPersonInfoServlet extends HttpServlet {
	private static final int MAX_COUNT_WORD_PER_ROW = 7;
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PersonDAO dao = new PersonDAO();
		
		
		HttpSession session = request.getSession();
		
		
		/**
		 *  Gets all person data from DB, returns ResultSet
		 */
		ResultSet rs = dao.createPersonFromDB((String) session.getAttribute("email"));
		
		// response.sendRedirect("./view/profile.html");

		/**
		 *  key - db column name; value - session attribute
		 */
		Map<String, String> map = new HashMap<String, String>();;
		
		FriendDAO daoFriends= new FriendDAO();
		List<Person>list= daoFriends.checkForNotification((String) session.getAttribute("email"));
		if(!list.isEmpty()){
			session.setAttribute("hasNotifications", "you hava a notification");
		}
		try {

			//---------------------------------------------------------------------------------
			while (rs.next()) {
				map.put("first_name", rs.getString("first_name"));
				map.put("last_name", rs.getString("last_name"));
				map.put("email", rs.getString("email"));
				map.put("about", rs.getString("about"));
				map.put("phone", rs.getString("phone"));
				map.put("location", rs.getString("location"));
				map.put("profilePic", rs.getString("profile_picture"));
				map.put("coverPic", rs.getString("cover_photo"));
				
				String isMale = rs.getString("isMale");

				if (isMale != null) {
					session.setAttribute("isMale", isMale);
				}
				for (Entry<String, String> entry : map.entrySet()) {
					if (entry.getValue() != null) {
						if (entry.getKey().equals("about")) {
							String splitText = splitText(entry.getValue());
							session.setAttribute("about", splitText);
						}
						session.setAttribute(entry.getKey(), entry.getValue());
					}
				}
			}

			//---------------------------------------------------------------------------------
			for (Entry<String, String> entry : map.entrySet()) {
				if (entry.getValue() != null) {
					System.out.println(session.getAttribute(entry.getKey()));
				}
			}

			response.sendRedirect("./getAllPosts");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//---------------------------------------------------------------------------------
	private String splitText(String value) {
		String[] array = value.split(" ");
		int wordCounter = 0;

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < array.length; i++) {
			if (wordCounter < MAX_COUNT_WORD_PER_ROW) {
				result.append(array[i]);
				result.append(" ");
				wordCounter++;
			} else {
				result.append("<br />");
				wordCounter = 0;
			}
		}
		return result.toString();
	}
}
