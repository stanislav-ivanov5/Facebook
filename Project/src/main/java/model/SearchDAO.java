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
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import database.DatabaseConnection;

/**
 *  Handles user's search queries
 * 
 *
 */
public class SearchDAO implements ISearchDAO {

	private static final String EMPTY_STRING = "";
	private static final int SECOND_PARAM = 2;
	private static final int FIRST_PARAM = 1;
	private static final int ONE_WORD = 1;
	private static final String SPACE_STRING = " ";
	private static final String ALPHANUMERICAL_CHARS_REGEX = "[^a-zA-Z0-9]";

	// key - user email, value - [key - db column name, value - db column value]
	// user fields[firstName, lastName, location]
	public Map<String, Map<String, String>> searchByName(String name) {
		if (name == null) {
			System.out.println("[SearchDAO] --- Invalid [name]=null");
			throw new InvalidParameterException("Invalid search input");
		}

		try {
			Connection con = DatabaseConnection.getInstance().getConnection();

			Map<String, Map<String, String>> map = executeUserQuery(name, con);

			return map;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new InvalidParameterException("We've an issue processing your request. Please try again later.");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InvalidParameterException("We've an issue processing your request. Please try again later.");
		}
	}

	// -----------------------------------------------------------------------------

	/**
	 *  key - email, value - person fields
	 * @param input
	 * @param con
	 * @return
	 */
	private Map<String, Map<String, String>> executeUserQuery(String input, Connection con) {
		if (input == null) {
			System.out.println("[SearchDAO] --- Invalid input [null]");
			throw new InvalidParameterException("Invalid search input");
		}

		// Gets user's input as an array of 'correct' words
		input = replaceInvalidChars(input);
		String[] words = getWords(input);

		Map<String, Map<String, String>> result = new HashMap<>();

		try {
			Stack<String> wordStack = new Stack<String>();
			wordStack.addAll(Arrays.asList(words));

			// Log
			System.out.println("[SearchDAO]\n--- [wordStack]");
			for (String string : wordStack) {
				System.out.println("---" + string);
			}

			// PreparedStatement for db queries

			int paramIndex1 = FIRST_PARAM;

			// -----------------------------------------------------------------------------

			// While there are words in the user's input add conditions to the
			// query
			PreparedStatement ps = null;
			ResultSet resultSet = null;
			String sql = null;
			int initialSize = wordStack.size();

			StringBuilder query = new StringBuilder(
					"SELECT first_name, last_name, location, email, profile_picture " + "FROM persons " + "WHERE ");

			while (!wordStack.isEmpty()) {

				int stackSize = wordStack.size();
				boolean passed = false;
				
				String word = wordStack.pop();
				stackSize--;
				
				if(!passed && stackSize == 0) {
						query.append(" first_name=? ");
						query.append(" OR last_name=? ");
					sql = query.toString();
					ps = con.prepareStatement(sql);
					
					System.out.println(sql);
					
					ps.setString(paramIndex1++, word);
					ps.setString(paramIndex1++, word);
					
					passed = true;
				}
				
				if(stackSize == 1) {
					query.append(" ( first_name=? ");
					query.append(" AND last_name=? ) ");
				}
				
				if (stackSize == 1) {
					
					String word2 = wordStack.pop();
					stackSize--;

					query.append(" OR ( last_name=? ");
					query.append(" AND first_name=? ) ");

					sql = query.toString();
					ps = con.prepareStatement(sql);
					
					System.out.println(sql);

					paramIndex1 = 1;
					
					ps.setString(paramIndex1++, word);
					ps.setString(paramIndex1++, word2);
					ps.setString(paramIndex1++, word);
					ps.setString(paramIndex1++, word2);
				}
			}
			resultSet = ps.executeQuery();

			System.out.println("[SearchDAO] --- [sql]=" + sql);

			// -----------------------------------------------------------------------------

			while (resultSet.next()) {
				Map<String, String> columnsData = new TreeMap<String, String>();

				// Save db columns names
				List<String> columnNames = Arrays.asList("first_name", "last_name", "location", "profile_picture");

				// Add columns names in the list
				for (String s : columnNames) {
					String value = resultSet.getString(s);
					if (value != null) {
						columnsData.put(s, value);
					}
				}

				// Add keys (emails) to map, add corresponding list afterward
				String email = resultSet.getString("email");
				result.put(email, columnsData);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[SearchDAO] -- SQLException - " + e.getMessage());
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
