package model;

import exceptions.InvalidParameterException;

public interface IPersonDAO {

	void addPerson(Person person) throws InvalidParameterException;
}
