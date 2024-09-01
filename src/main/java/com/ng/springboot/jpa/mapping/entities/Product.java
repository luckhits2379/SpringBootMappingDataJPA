package com.ng.springboot.jpa.mapping.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String name;
	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	ProductDetails productDetails;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
		this.productDetails.setProduct(this);
	}

	public Product(String name, ProductDetails productDetails) {
		super();
		this.name = name;
		this.productDetails = productDetails;
		this.productDetails.setProduct(this);
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", productDetails=" + productDetails + "]";
	}

}
