package com.assess.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.assess.demo.model.Person;


public interface PersonRepository extends JpaRepository<Person, Long> {
	
}
