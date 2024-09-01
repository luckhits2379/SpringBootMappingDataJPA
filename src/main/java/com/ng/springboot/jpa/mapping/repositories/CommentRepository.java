package com.ng.springboot.jpa.mapping.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ng.springboot.jpa.mapping.entities.Comment;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

}
