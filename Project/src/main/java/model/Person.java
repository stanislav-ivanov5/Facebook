package model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import exceptions.InvalidParameterException;

public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4546391323648164383L;
	private static final String ALPHANUMERICAL_CHARS_REGEX = "[a-zA-Z0-9]*";
	private static final String ONLY_DIGITS_REGEX = "[0-9]*";
	private static final int MIN_UPPERCASE_LETTERS = 1;
	private static final int MIN_LOWERCASE_LETTERS = 2;
	private static final int MIN_DIGIT_NUMBER = 1;
	private static final int MIN_PASS_LENGTH = 8;
	private static final String EMPTY_STRING = "";
	private static final String MAIL_AT_SIGN = "@";
	private static final String COM_DOMAIN_SUFFIX = ".com";
	private static final String BG_DOMAIN_SUFFIX = ".bg";

	private String email;
	private String firstName;
	private String lastName;
	private String gender;
	private String profilePicPath;
	private String about;
	private String password;
	private String phone;
	private String location;
	private List<Post>posts;
	
	public Person() {
	}

	public Person(String firstName, String lastName) throws InvalidParameterException {
		setFirstName(firstName);
		setLastName(lastName);
	}
	public Person(String firstName, String lastName, String profilePic) throws InvalidParameterException{
		this(firstName,lastName);
		this.setProfilePicPath(profilePic);
	}
	public Person(String firstName, String lastName, String email, String gender) throws InvalidParameterException {

		this(firstName, lastName);
		setEmail(email);
		setGender(gender);
	}

	public Person(String firstName, String lastName, String email, String gender, String password)
			throws InvalidParameterException {
		this(firstName, lastName, email, gender);
		setPassword(password);
	}

	public Person(String firstName, String lastName, String email, String gender, String about, String phone,
			String location) throws InvalidParameterException {
		this(firstName, lastName, email, gender, about);
		setPhone(phone);
		setLocation(location);
	}

	//---------------------------------------------------------------------------------
	
	public void setEmail(String email) throws InvalidParameterException {

		if (email == null) {
			System.out.println("[Person] Invalid email address.");
			throw new InvalidParameterException("Invalid email address.");
		} else {
			
			if (!email.contains(MAIL_AT_SIGN)) {
				System.out.println("[Person] Invalid email address.");
				throw new InvalidParameterException("Invalid email address.");
			}
			String localPart = email.substring(0, email.indexOf(MAIL_AT_SIGN));
			String domainPart = email.substring(email.indexOf(MAIL_AT_SIGN), email.length());

			boolean validDomainName = domainPart.contains(COM_DOMAIN_SUFFIX) || domainPart.contains(BG_DOMAIN_SUFFIX);

			if (localPart.length() > 0 && domainPart.length() > 0 && validDomainName) {
				this.email = email;
				System.out.println("meila e gotov");

			} else {
				System.out.println("[Person] Invalid email address.");
				throw new InvalidParameterException("Invalid email address.");
			}
		}
	}

	// -----------------------------------------------------------------------

	public void setFirstName(String firstName) throws InvalidParameterException {
		if(firstName == null) {
			System.out.println("[Person] Invalid first name.");
			throw new InvalidParameterException("Invalid first name.");
		}
		
		boolean validFirstName = !firstName.equals(EMPTY_STRING) && validChars(firstName);

		if (validFirstName) {
			this.firstName = firstName;
		} else {
			System.out.println("[Person] Invalid first name.");
			throw new InvalidParameterException("Invalid first name.");
		}
	}

	public void setLastName(String lastName) throws InvalidParameterException {
		if(lastName == null) {
			System.out.println("[Person] Invalid last name.");
			throw new InvalidParameterException("Invalid last name.");
		}
		
		boolean validLastName = !lastName.equals(EMPTY_STRING);
		// && validChars(lastName);

		if (validLastName) {
			this.lastName = lastName;
		} else {
			System.out.println("[Person] Invalid last name.");
			throw new InvalidParameterException("Invalid last name.");
		}
	}

	public void setAbout(String about) throws InvalidParameterException {
		if(about == null) {
			return;
		}
		boolean validAboutText = about != null && !about.equals(EMPTY_STRING);

		if (validAboutText) {
			this.about = about;
		} else {
			throw new InvalidParameterException("[Person] Invalid about.");
		}
	}

	/**
	 *  -----------------------------------------------------------------------
	 * @param password
	 * @throws InvalidParameterException
	 * Password should have a min. of 8 chars, min 1 uppercase
	 * Letter, min. 2 lowercase ones, min 1 digit
	 */

	public void setPassword(String password) throws InvalidParameterException {

		if (password == null) {
			System.out.println("[Person] Null password.");
			throw new InvalidParameterException("Invalid password.");

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
				this.password = password;

			} else {
				throw new InvalidParameterException("[Person] Invalid password.");
			}
		}
	}
	
	public void setPhone(String phone) {
		if(phone == null) {
			return;
		}
		
		if (phone != null && phone.length() == 10) {
			boolean containsOnlyDigits = phone.matches(ONLY_DIGITS_REGEX);

			if (containsOnlyDigits) {
				this.phone = phone;
			}
		}
	}

	// -----------------------------------------------------------------------
	// Getters and setters

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	protected String getPassword() {
		return password;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		if (location != null) {
			this.location = location;
		}
	}

	public String getPhone() {
		return phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProfilePicPath() {
		return profilePicPath;
	}

	public void setProfilePicPath(String picName){
		if(picName!=null){
			this.profilePicPath=picName;
		}
	}
	public String getAbout() {
		return about;
	}

	private boolean validChars(String input) {
		if(input == null) {
			return false;
		}
		
		boolean containsValidChars = input.matches(ALPHANUMERICAL_CHARS_REGEX);

		return containsValidChars;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		if(posts!=null)
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "Person [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", profilePicPath=" + profilePicPath + ", about=" + about + ", password=" + password + ", phone="
				+ phone + ", location=" + location + "]";
	}

	
}
