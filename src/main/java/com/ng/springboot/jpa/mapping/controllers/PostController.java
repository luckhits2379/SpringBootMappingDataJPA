package com.ng.springboot.jpa.mapping.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ng.springboot.jpa.mapping.entities.Post;
import com.ng.springboot.jpa.mapping.repositories.PostRepository;

@Controller
public class PostController {

	@Autowired
	PostRepository postRepository;

	@GetMapping(value = "/posts/{id}")
	public ResponseEntity<Optional<Post>> getPost(@PathVariable(required = true) String id) {

		return ResponseEntity.ok(postRepository.findById(Integer.parseInt(id)));

	}

	@PostMapping(value = "/posts/")
	public ResponseEntity<Void> createPost(@RequestBody Post post) throws URISyntaxException {

		Post createdPost = postRepository.save(post);

		return ResponseEntity.created(new URI("/posts/" + createdPost.getId())).build();

	}

}
