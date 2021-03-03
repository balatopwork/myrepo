package com.assess.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assess.demo.dao.PersonRepository;
import com.assess.demo.exception.RecordNotFoundException;
import com.assess.demo.model.Person;



@Service
@Transactional
public class PersonServiceImpl implements  PersonService {
     
    @Autowired
    private PersonRepository repository; 
    
    
    public List<Person> getAllPersons()
    {
        List<Person> personList = repository.findAll();
         
        if(personList.size() > 0) {
            return personList;
        } else {
            return new ArrayList<Person>();
        }
    }

	@Override
	public Person getPersonById(long personId) {
		Optional<Person> person = repository.findById(personId);
        
        if(person.isPresent()) {
            return person.get();
        } else {
            throw new RecordNotFoundException("No Person record exist for given id -"+personId);
        }
	}
    
    @Override
	public Person createPerson(Person person) {
		return repository.save(person);
	}

	@Override
	public Person updatePerson(Person entity) {
		Optional<Person> person = repository.findById(entity.getId());
        
        if(person.isPresent())
        {
        	Person newEntity = person.get();
        	newEntity.setFirst_name(entity.getFirst_name());
        	newEntity.setLast_name(entity.getLast_name());
            newEntity.setAge(entity.getAge());
            newEntity.setFavourite_colour(entity.getFavourite_colour());
 
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
        	throw new RecordNotFoundException("Record not found with id : " + entity.getId());
        }
	}
	
	

	@Override
	public void deletePerson(long id){
		Optional<Person> productDb = repository.findById(id);
        
        if(productDb.isPresent())
        {
            repository.delete(productDb.get());
        } else {
            throw new RecordNotFoundException("No Person record exist for given id - "+id);
        }
		
	}

}