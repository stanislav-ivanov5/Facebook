package model;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import database.connection.DatabaseConnection;

// Handles user's search queries
public class SearchDAO {

	private static final String EMPTY_STRING = "";
	private static final int SECOND_PARAM = 2;
	private static final int FIRST_PARAM = 1;
	private static final int ONE_WORD = 1;
	private static final String SPACE_STRING = " ";
	private static final String ALPHANUMERICAL_CHARS_REGEX = "[^a-zA-Z0-9]";

	// key - user email, value - user fields[firstName, lastName, location]
	public Map<String, List<String>> searchByName(String name) {
		if (name == null) {
			System.out.println("[SearchDAO] --- Invalid [name]=null");
			throw new InvalidParameterException("Invalid search input");
		}

		try {
			Connection con = DatabaseConnection.getInstance().getConnection();

			String query = null;

			Map<String, List<String>> map = executeUserQuery(name, con);
			
			return map;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new InvalidParameterException("We've an issue processing your request. Please try again later.");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InvalidParameterException("We've an issue processing your request. Please try again later.");
		}
	}

	//-----------------------------------------------------------------------------
	
	// key - email, value - person fields
	private Map<String, List<String>> executeUserQuery(String input, Connection con) {
		if (input == null) {
			System.out.println("[SearchDAO] --- Invalid input [null]");
			throw new InvalidParameterException("Invalid search input");
		}

		// Gets user's input as an array of 'correct' words
		input = replaceInvalidChars(input);
		String[] words = getWords(input);

		StringBuilder query = new StringBuilder("SELECT first_name, last_name, location, email " + "FROM persons "
				+ "WHERE first_name=? " + "OR last_name=? ");
		String sql = query.toString();
		Map<String, List<String>> result = new HashMap<>();

		try {
			Stack<String> wordStack = new Stack<String>();
			wordStack.addAll(Arrays.asList(words));

			// PreparedStatement for db queries
			PreparedStatement ps = con.prepareStatement(sql);
			int paramIndex1 = FIRST_PARAM;
			int paramIndex2 = SECOND_PARAM;

			//-----------------------------------------------------------------------------
			
			// While there are words in the user's input add conditions to the
			// query
			while (!wordStack.isEmpty()) {

				String word = wordStack.pop();

				query = query.append(" OR first_name=?");
				query = query.append(" OR last_name=?");
				sql = query.toString();

				ps.setString(paramIndex1++, word);
				ps.setString(paramIndex2++, word);

			}
			System.out.println("[SearchDAO] --- [sql]=" + sql);

			ResultSet resultSet = ps.executeQuery();

			//-----------------------------------------------------------------------------
			
			while (resultSet.next()) {
				List<String> columns = Arrays.asList("first_name", "last_name", "location");

				// Add it to map later 
				List<String> list = new ArrayList<String>();
				
				// Add columns names in the list
				for(String s : columns) {
					String column = resultSet.getString(s);
					if(column != null) {
						list.add(column);						
					}
				}
				
				// Add keys (emails) to map, add corresponding list afterward
				String email = resultSet.getString("email");
				result.put(email, list);
				
				
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new InvalidParameterException("Invalid search input");
		}
		return Collections.unmodifiableMap(result);
	}

	private String[] getWords(String input) {
		return input.split(SPACE_STRING);
	}

	private String replaceInvalidChars(String input) {
		input = input.trim();
		input = input.toLowerCase();
		input = input.replaceAll(ALPHANUMERICAL_CHARS_REGEX, SPACE_STRING);
		input = input.trim();
		return input;
	}
}
