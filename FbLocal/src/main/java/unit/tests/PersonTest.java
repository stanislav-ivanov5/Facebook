package unit.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.InvalidParameterException;
import model.Person;

public class PersonTest {

	@Test
	public void testPerson() throws InvalidParameterException {
		Person p = new Person("ivan", "asdf", "menov@gmail.com", "female", "Parola555667");	
		assertNotNull(p);
	}
	
	@Test(expected=InvalidParameterException.class)
	public void testPassword() throws InvalidParameterException{
		Person p = new Person(); 
		String password= "pass";
		p.setPassword(password);
	}
	 
	@Test(expected=InvalidParameterException.class)
	public void testEmail() throws InvalidParameterException{
		Person p = new Person();
		String email= "asdf";
		p.setEmail(email);
	}
	
	
}
