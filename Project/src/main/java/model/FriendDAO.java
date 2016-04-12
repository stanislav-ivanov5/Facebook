package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import database.DatabaseConnection;
import exceptions.InvalidParameterException;

public class FriendDAO {

	public boolean checkIfSuchRequestExists(String emailPersonPending, String emailFriendPending)
			throws InvalidParameterException {
		String query = "SELECT * from pending_friendship_requests WHERE "
				+ "(email_person_pending=? AND email_friend_pending=?) OR "
				+ "(email_person_pending=? AND email_friend_pending=?) ";
		try {
			Connection con = DatabaseConnection.getInstance().getConnection();
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, emailPersonPending);
			stm.setString(2, emailFriendPending);
			stm.setString(3, emailFriendPending);
			stm.setString(4, emailPersonPending);

			ResultSet result = stm.executeQuery();
			if (result.next()) {
				return true;
			}
			return false;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		throw new InvalidParameterException();
	}

	public void addPersonToFriendsList(String emailSenderOfRequest, String emailRequestAdmitter) {
		if (emailSenderOfRequest != null && emailRequestAdmitter != null) {
			try {
				Connection con = DatabaseConnection.getInstance().getConnection();
				String query = "INSERT INTO friends(email_person, email_friend) values(?,?);";
				PreparedStatement stm = con.prepareStatement(query);
				stm.setString(1, emailSenderOfRequest);
				stm.setString(2, emailRequestAdmitter);
				stm.executeUpdate();

				String querySec = "INSERT INTO friends(email_person, email_friend) values(?,?);";
				PreparedStatement stmSec = con.prepareStatement(querySec);
				stmSec.setString(1, emailRequestAdmitter);
				stmSec.setString(2, emailSenderOfRequest);
				stmSec.executeUpdate();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// Checking if you have notification and return List of
	// people who notified you

	public List<Person> checkForNotification(String email) {
		List<Person> listPersons = new ArrayList<Person>();
		try {
			Connection con = DatabaseConnection.getInstance().getConnection();
			String query = "SELECT email_person_pending FROM pending_friendship_requests "
					+ "WHERE email_friend_pending=?";
			PreparedStatement stmFirst = con.prepareStatement(query);
			stmFirst.setString(1, email);
			ResultSet resultFirst = stmFirst.executeQuery();
			List<String> listEmails = new ArrayList<String>();
			while (resultFirst.next()) {
				listEmails.add(resultFirst.getString("email_person_pending"));

				System.out.println(
						"email of the person who send the request " + resultFirst.getString("email_person_pending"));
			}

			for (String emailOfPerson : listEmails) {
				String querySecond = "SELECT email, first_name, last_name, profile_picture "
						+ "FROM persons WHERE email=?";
				PreparedStatement stmSecond = con.prepareStatement(querySecond);
				stmSecond.setString(1, emailOfPerson);
				ResultSet resultSecond = stmSecond.executeQuery();
				System.out.println("--------------------------");
				while (resultSecond.next()) {
					String senderEmail = resultSecond.getString("email");
					String firstName = resultSecond.getString("first_name");
					System.out.println(firstName);
					String lastName = resultSecond.getString("last_name");
					System.out.println(lastName);
					String profilePic = resultSecond.getString("profile_picture");
					System.out.println(profilePic);
					Person person = new Person();
					try {
						System.out.println("Person who send the request info:");
						person.setFirstName(firstName);
						System.out.println(firstName);
						person.setLastName(lastName);
						System.out.println(lastName);
						person.setEmail(senderEmail);
						System.out.println(senderEmail);
						if (profilePic != null) {
							person.setProfilePicPath(profilePic);
						}
					} catch (InvalidParameterException e) {

						e.printStackTrace();
					}
					listPersons.add(person);
				}
				return listPersons;
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listPersons;
	}

	public void addPendingRequest(String emailOfPersonToSendRequestTo, String emailOfTheSenderOfTheRequest) {
		try {
			Connection con = DatabaseConnection.getInstance().getConnection();
			String query = "INSERT INTO pending_friendship_requests values(? , ?);";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, emailOfTheSenderOfTheRequest);
			stm.setString(2, emailOfPersonToSendRequestTo);
			stm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void removePendingRequestFromPerson(String emailSender) {
		try {
			Connection con = DatabaseConnection.getInstance().getConnection();
			String query = "DELETE from pending_friendship_requests WHERE email_person_pending=?;";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, emailSender);
			stm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Person> getAllFriends(String email) {
		if (email != null) {
			List<Person> friends = new ArrayList<Person>();
			try {
				Connection con = DatabaseConnection.getInstance().getConnection();
				String query = "SELECT email_friend FROM friends WHERE email_person=?";
				PreparedStatement stm = con.prepareStatement(query);
				stm.setString(1, email);
				ResultSet result = stm.executeQuery();
				List<String> friendEmails = new ArrayList<String>();

				while (result.next()) {
					String emailOfFriend = result.getString("email_friend");
					friendEmails.add(emailOfFriend);
				}
				System.out.println("List of friends");
				for (String string : friendEmails) {
					System.out.println(string);
				}
				ResultSet resultSec=null;
				for (String frEmail : friendEmails) {
					String querySec = "SELECT first_name, last_name, profile_picture" +
							" FROM persons WHERE email=?";
					System.out.println("SELECT first_name, last_name, profile_picture" +
							" FROM persons WHERE email="+frEmail);
					PreparedStatement stmSec = con.prepareStatement(querySec);
					String em= frEmail;
					stmSec.setString(1, em);
					 resultSec = stmSec.executeQuery();
					 Person person =null;
					 while (resultSec.next()) {
							String firstName = resultSec.getString("first_name");
							String lastName = resultSec.getString("last_name");
							String profilePic = resultSec.getString("profile_picture");
							
							try {
								person= new Person();
								person.setFirstName(firstName);
								person.setLastName(lastName);
								if (profilePic != null) {
									person.setProfilePicPath(profilePic);
								}
								person.setEmail(frEmail);
								friends.add(person);
								
								System.out.println("Person to add to list:");
								System.out.println(person);
								
							} catch (InvalidParameterException e) {
								e.printStackTrace();
							}
						}
				}return friends;
				

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
