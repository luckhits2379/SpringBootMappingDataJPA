package com.ng.springboot.jpa.mapping.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String comment;

	@ManyToOne
	@JoinColumn
	private User commenter;

	@ManyToOne
	@JsonBackReference
	private Post post;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getCommenter() {
		return commenter;
	}

	public void setCommenter(User commenter) {
		this.commenter = commenter;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Comment(String comment, User commenter, Post post) {
		super();
		this.comment = comment;
		this.commenter = commenter;
		this.post = post;
	}

	public Comment(String comment, User commenter) {
		super();
		this.comment = comment;
		this.commenter = commenter;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", commenter=" + commenter + ", post=" + post + "]";
	}

}
