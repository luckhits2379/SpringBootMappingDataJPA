package com.ng.springboot.jpa.mapping.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class ProductDetails {

	@Id
	int id;

	String description;

	@OneToOne
	@MapsId
	@JoinColumn(name = "id")
	Product product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductDetails(String description) {
		super();
		this.description = description;
	}

	public ProductDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ProductDetails [product=" + product + ", description=" + description + "]";
	}

}
