package com.ng.springboot.jpa.mapping.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ng.springboot.jpa.mapping.entities.Comment;
import com.ng.springboot.jpa.mapping.entities.Post;
import com.ng.springboot.jpa.mapping.repositories.CommentRepository;
import com.ng.springboot.jpa.mapping.repositories.PostRepository;

@Controller
public class CommentControllerFromPostSide {

	@Autowired
	PostRepository postRepository;

	@Autowired
	CommentRepository commentRepository;

	@DeleteMapping(value = "/comments_parent/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable(required = true) String id) {

		Optional<Comment> comment = commentRepository.findById(Integer.parseInt(id));

		Post post = comment.get().getPost();

		post.removeComment(comment.get().getId());

		postRepository.save(post);

		return ResponseEntity.ok().build();

	}

	@PostMapping(value = "/comments_parent/")
	public ResponseEntity<Void> addComment(@RequestBody Comment comment) {

		Optional<Post> post = postRepository.findById(comment.getPost().getId());

		post.get().addComment(comment);

		postRepository.save(post.get());

		return ResponseEntity.ok().build();

	}
}
