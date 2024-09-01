package com.ng.springboot.jpa.mapping.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ng.springboot.jpa.mapping.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
