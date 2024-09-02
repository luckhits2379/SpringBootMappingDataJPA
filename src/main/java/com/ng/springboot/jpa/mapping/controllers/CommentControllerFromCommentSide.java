package com.ng.springboot.jpa.mapping.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ng.springboot.jpa.mapping.entities.Comment;
import com.ng.springboot.jpa.mapping.repositories.CommentRepository;

@Controller
public class CommentControllerFromCommentSide {

	@Autowired
	CommentRepository commentRepository;

	@DeleteMapping(value = "/comments_child/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable(required = true) String id) {

		System.out.println("inside delete comment from child entity");

		// both below methods not working because of bidirectional mapping and mapping
		// is not syncronized as parent entity is still may holding this child in
		// current session, to work this, we need to add preremove in comment object and
		// then going to parent and remove current comment then call this method

		commentRepository.deleteById(Integer.parseInt(id));

		// Optional<Comment> optionalComment =
		// commentRepository.findById(Integer.parseInt(id));

		// commentRepository.delete(optionalComment.get());

		// commentRepository.deleteById(Integer.parseInt(id));

		return ResponseEntity.ok().build();

	}

	@GetMapping(value = "/comments_child/{id}")
	public ResponseEntity<Optional<Comment>> getComment(@PathVariable(required = true) String id) {

		Optional<Comment> optionalComment = commentRepository.findById(Integer.parseInt(id));

		return ResponseEntity.ok(optionalComment);

	}

	@PostMapping(value = "/comments_child/")
	public ResponseEntity<Void> addComment(@RequestBody Comment comment) {

		commentRepository.save(comment);

		return ResponseEntity.ok().build();

	}
}
