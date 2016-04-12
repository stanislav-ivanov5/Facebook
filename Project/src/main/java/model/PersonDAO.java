package model;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import database.DatabaseConnection;
import exceptions.InvalidParameterException;

public class PersonDAO  implements IPersonDAO{

	private static final int EMPTY_LIST_SIZE = 0;
	private static final int MIN_UPPERCASE_LETTERS = 1;
	private static final int MIN_LOWERCASE_LETTERS = 2;
	private static final int MIN_DIGIT_NUMBER = 1;
	private static final int MIN_PASS_LENGTH = 8;


	// Add person to DB
	@Override
	public void addPerson(Person person) throws InvalidParameterException {
		if (person != null) {
			try {
				Connection con = DatabaseConnection.getInstance().getConnection();
				String query = "INSERT INTO facebook.persons(first_name,last_name,email,isMale,password)"
						+ " values(?,?,?,?,MD5(?))";

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
		} else {
			System.out.println("[PersonDAO] --- Object given to the method is null.");
			throw new InvalidParameterException("Invalid input.");
		}

	}

	public void updateCoverPhoto(String fileName, String email) throws InvalidParameterException {
		if (fileName == null || email == null) {
			System.out.println("invalid filename or email");
			throw new InvalidParameterException("Invalid parameters.");

		} else {
			try {
				Connection con = DatabaseConnection.getInstance().getConnection();
				String query = "UPDATE persons SET cover_photo=? WHERE email=?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, fileName);
				ps.setString(2, email);
				ps.executeUpdate();

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// Save photo uri to db
	public void updateProfilePhotoPath(String fileName, String email) {
		if (fileName == null | email == null) {
			System.out.println("[PersonDAO] --- updateProfilePhotoPath() --- invalid input");
		}

		try {
			Connection con = DatabaseConnection.getInstance().getConnection();

			String query = "UPDATE persons SET profile_picture=? WHERE email=?";
			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, fileName);
			ps.setString(2, email);
			ps.executeUpdate();

			// ----------------------------------------------------------------------
			System.out.println("[PersonDAO] --- updateProfilePhotoPath --- successfully ");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// ---------------------------------------------------------------------------------

	/**
	 *  Get existing person from DB
	 * @param email
	 * @return
	 */
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

	// ---------------------------------------------------------------------------------
	
	//Check for existing person in the database when trying to login
	
	@Override
	public Person getPerson(String email)  {
		Person person = null;
		try {
			Connection con = DatabaseConnection.getInstance().getConnection();
			String query = "SELECT * FROM persons WHERE email =? ";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, email);
			
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				String firstName = result.getString(1);
				String lastName = result.getString(2);
				String profilePic= result.getString("profile_picture");
				
				try {
					person = new Person(firstName, lastName);
					if(profilePic!=null){
						person.setProfilePicPath(profilePic);
					}
				} catch (InvalidParameterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return person;
	}	
	public Person personLoginCheck(String email, String password) throws InvalidParameterException {
		Person person = null;
		try {
			
			MessageDigest digest= MessageDigest.getInstance("MD5");
			digest.update(password.getBytes(), 0, password.length());
			String encryptedText=new BigInteger(1, digest.digest()).toString(16);
			
			Connection con = DatabaseConnection.getInstance().getConnection();
			String query = "SELECT * FROM persons WHERE email =?  AND password=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, encryptedText);
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
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return person;
	}

	// ---------------------------------------------------------------------------------

	public void updatePassword(String password, String email) {
		try {
			Connection con = DatabaseConnection.getInstance().getConnection();
			String query = "UPDATE persons SET password=MD5(?) WHERE email=?";
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

	/**
	 *  Retrive current Password from db and compares it to the request parameter
	 * @param email
	 * @param currentPassword
	 * @return
	 */
	public boolean retrieveCurrentPassword(String email, String currentPassword) {
		try {		
			String encryptPass="";
			String pass = null;
			Connection con = DatabaseConnection.getInstance().getConnection();
			String query = "SELECT password FROM persons WHERE email= ?";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, email);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				pass = rs.getString("password");
			}
			MessageDigest digest= MessageDigest.getInstance("MD5");
			digest.update(currentPassword.getBytes(), 0, currentPassword.length());
			encryptPass = new BigInteger(1, digest.digest()).toString(16);
			
			if (currentPassword == null || !encryptPass.equals(pass)) {
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// ---------------------------------------------------------------------------------

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
