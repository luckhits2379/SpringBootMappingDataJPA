package com.ng.springboot.jpa.mapping.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ng.springboot.jpa.mapping.entities.Post;

public interface PostRepository extends CrudRepository<Post, Integer> {

}