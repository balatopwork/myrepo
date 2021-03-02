package com.assess.demo.service;

import java.util.List;

import com.assess.demo.exception.RecordNotFoundException;
import com.assess.demo.model.Person;


public interface PersonService {
	Person createPerson(Person person);

	Person updatePerson(Person person);

	List<Person> getAllPersons();

	Person getPersonById(long personId); 

	void deletePerson(long id);
}
