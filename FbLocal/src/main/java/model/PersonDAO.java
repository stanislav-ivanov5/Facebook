package model;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import database.connection.DatabaseConnection;
import exceptions.InvalidParameterException;

public class PersonDAO implements IPersonDAO {

	private static final int EMPTY_LIST_SIZE = 0;
	private static final int MIN_UPPERCASE_LETTERS = 1;
	private static final int MIN_LOWERCASE_LETTERS = 2;
	private static final int MIN_DIGIT_NUMBER = 1;
	private static final int MIN_PASS_LENGTH = 8;
	private static final String IMG_FOLDER_PATH = "D:\\WebProjects\\Facebook\\FacebookTMP\\WebContent";

	// Add person to DB
	public void addPerson(Person person) throws InvalidParameterException {
		if (person != null) {
			try {
				Connection con = DatabaseConnection.getInstance().getConnection();
				String query = "INSERT INTO facebook.persons(first_name,last_name,email,isMale,password)"
						+ " values(?,?,?,?,?)";

				PreparedStatement statement = con.prepareStatement(query);
				statement.setString(1, person.getFirstName());
				statement.setString(2, person.getLastName());
				statement.setString(3, person.getEmail());
				statement.setString(4, person.getGender());
				statement.setString(5, person.getPassword());

				statement.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else
			System.out.println("[PersonDAO] --- Object given to the method is null.");
			throw new InvalidParameterException("Invalid input.");
	}

	// Save photo uri to db
	public void updateProfilePhotoPath(String fileName, String email) {
		if(fileName == null | email == null) {
			System.out.println("[PersonDAO] --- updateProfilePhotoPath() --- invalid input");
		}
		
		try {
			Connection con = DatabaseConnection.getInstance().getConnection();

			String query = "UPDATE persons SET profile_picture=? WHERE email=?";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, fileName);
			ps.setString(2, email);
			ps.executeUpdate();
			
			//----------------------------------------------------------------------
			System.out.println("[PersonDAO] --- updateProfilePhotoPath --- successfully ");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	//---------------------------------------------------------------------------------
	
	// Get existing person from DB
	public ResultSet createPersonFromDB(String email) {
		Person person = null;
		ResultSet result = null;

		try {
			Connection con = DatabaseConnection.getInstance().getConnection();
			String query = "SELECT * FROM persons WHERE email =? ";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, email);
			result = statement.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	//---------------------------------------------------------------------------------
	
	public Person personLoginCheck(String email, String password) throws InvalidParameterException {
		Person person = null;
		try {
			Connection con = DatabaseConnection.getInstance().getConnection();
			String query = "SELECT * FROM persons WHERE email =?  AND password=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				String firstName = result.getString(1);
				String lastName = result.getString(2);
				person = new Person(firstName, lastName);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return person;
	}

	//---------------------------------------------------------------------------------
	
	public void updatePassword(String password, String email) {
		try {
			Connection con = DatabaseConnection.getInstance().getConnection();
			String query = "UPDATE persons SET password=? WHERE email=?";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, password);
			stm.setString(2, email);
			stm.executeUpdate();
			System.out.println("[PersonDAO] --- updated password!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// retrive current Password from db and compares it to the request parameter
	public boolean retrieveCurrentPassword(String email, String currentPassword) {
		try {
			String pass = null;
			Connection con = DatabaseConnection.getInstance().getConnection();
			String query = "SELECT password FROM persons WHERE email= ?";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, email);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				pass = rs.getString("password");
			}
			if (currentPassword == null || !currentPassword.equals(pass)) {
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//---------------------------------------------------------------------------------
	
	public void updatePersonInfo(String email, String firstName, String lastName, String location, String phone,
			String about) {

		Connection con = null;

		try {
			con = DatabaseConnection.getInstance().getConnection();

			List<String> parameters = Arrays.asList(firstName, lastName, location, phone, about);
			List<String> columns = Arrays.asList("first_name", "last_name", "location", "phone", "about");

			for (int p = 0; p < parameters.size(); p++) {
				String columnName = columns.get(p);
				String parameter = parameters.get(p);

				if (parameter != null) {
					if (parameter.length() > 0) {
//						String query = "UPDATE persons SET " + columnName + "='" + parameter + "' WHERE email='" + email
//								+ "'";
//						Statement stmt = con.createStatement();						
//						stmt.executeUpdate(query);
						
						String sql = "UPDATE persons SET " + columnName + "=? WHERE email=?";
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(1, parameter);
						ps.setString(2, email);
						
						ps.executeUpdate();

						System.out.println("[PersonDAO] updatePersonInfo() --- updated");
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean passwordValidate(String password) {

		if (password == null) {
			return false;

		} else {

			int size = 0;
			int aDigit = 0;
			int upperCaseLetters = 0;
			int lowerCaseLetters = 0;

			for (int index = 0; index < password.length(); index++) {
				size++;
				if (Character.isDigit(password.charAt(index))) {
					aDigit++;
				}
				if (Character.isUpperCase(password.charAt(index))) {
					upperCaseLetters++;
				}
				if (Character.isLowerCase(password.charAt(index))) {
					lowerCaseLetters++;
				}
			}

			boolean validPassword = size >= MIN_PASS_LENGTH && aDigit >= MIN_DIGIT_NUMBER
					&& lowerCaseLetters >= MIN_LOWERCASE_LETTERS && upperCaseLetters >= MIN_UPPERCASE_LETTERS;

			if (validPassword) {
				return true;

			} else {
				return false;
			}
		}
	}
}
