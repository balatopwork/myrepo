package com.assess.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assess.demo.exception.RecordNotFoundException;
import com.assess.demo.model.Person;
import com.assess.demo.service.PersonService;



@RestController
@RequestMapping("/api")
public class PersonController {
	
	@Autowired
	private PersonService service; 
	
	@GetMapping("/persons")
	public ResponseEntity<List<Person>> getAllPerson(){
		return ResponseEntity.ok().body(service.getAllPersons());
	}
	
	@GetMapping("/persons/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable long id){
		return ResponseEntity.ok().body(service.getPersonById(id));
	}
	
	@PostMapping("/persons")
	public ResponseEntity<Person> createPerson(@RequestBody Person person){
		return ResponseEntity.ok().body(this.service.createPerson(person));
	}
	
	@PutMapping("/persons/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable long id, @RequestBody Person person){
		person.setId(id);
		return ResponseEntity.ok().body(this.service.updatePerson(person));
	}

	@DeleteMapping("/persons/{id}")
	public HttpStatus deletePersons(@PathVariable long id){
		this.service.deletePerson(id);
		return HttpStatus.OK;
	}
	

}
