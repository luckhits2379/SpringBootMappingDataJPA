package com.ng.springboot.jpa.mapping.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private String content;

	// mapped by will not create a separate table for mapping
	// if we don't use mapped by, it will create another table for mapping
	// this will maintain association on 'post' property of comment entity
	// orhanremoval true will remove all comments related to this post;
	// The fetch attribute is used in a one-to-many relationship default is LEAZY
	// The cascade attribute is used in a one-to-many relationship to specify how
	// changes to the parent entity should be cascaded to the associated entities.
	// this is parent entitity but ownership of mapping is with child entity Comment
	// The orphanRemoval attribute is used in a one-to-many relationship to specify
	// whether associated entities should be removed from the database when they are
	// no longer referenced by the parent entity. Orphan removeal will only work in
	// code, it wont delete orpahned record if mapping removed directly in db
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Comment> comments = new ArrayList<Comment>();

	@ManyToOne
	@JoinColumn
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setComment(Comment comment) {
		this.comments.add(comment);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", content=" + content + ", comments=" + comments + ", user=" + user + "]";
	}

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void addComment(Comment comment) {

		comment.setPost(this);
		comments.add(comment);

	}

	public boolean removeComment(int id) {

		for (Comment comment : comments) {

			if (comment.getId() == id) {

				comments.remove(comment);

				return true;
			}
		}

		return false;

	}

}
