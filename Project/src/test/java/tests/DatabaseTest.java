package tests;

import org.junit.Test;

import database.DatabaseConnection;
import exceptions.InvalidParameterException;
import model.Person;
import model.PersonDAO;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseTest {

	@Test
	public void testConnection() throws ClassNotFoundException, SQLException {
		DatabaseConnection conDB = DatabaseConnection.getInstance();
		Connection connection = conDB.getConnection();
		assertNotNull(connection);
	}

	@Test
	public void testGetPersonFromDB() {
		PersonDAO dao = new PersonDAO();
		String email = "ivanpetrov@gmail.com";
		String password = "IvanPetrov123";
		try {
			Person person = dao.personLoginCheck(email, password);
			assertNotNull(person);
		} catch (InvalidParameterException e) {
			e.printStackTrace();
		}
	}
}
