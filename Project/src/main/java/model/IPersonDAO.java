package model;

import exceptions.InvalidParameterException;

public interface IPersonDAO {

	public Person getPerson(String email);
	
	void addPerson(Person person) throws InvalidParameterException;

	Person personLoginCheck(String email, String password) throws InvalidParameterException;

	void updatePersonInfo(String email, String firstName, String lastName, String location, String phone, String about);

}
