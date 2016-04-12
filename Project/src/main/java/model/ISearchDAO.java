package model;

import java.util.Map;

public interface ISearchDAO {

	/**
	 *  key - user email, value - [key - db column name, value - db column value]
	 *  user fields[firstName, lastName, location]
	 * @param name
	 * @return
	 */
	Map<String, Map<String, String>> searchByName(String name);

}