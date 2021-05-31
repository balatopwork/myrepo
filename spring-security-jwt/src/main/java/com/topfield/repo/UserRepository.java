package com.topfield.repo;

import org.springframework.data.repository.CrudRepository;

import com.topfield.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
    User findByUsername(String username);
}