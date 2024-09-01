package com.ng.springboot.jpa.mapping.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ng.springboot.jpa.mapping.entities.ProductDetails;

public interface ProductDetailsRepository extends CrudRepository<ProductDetails, Integer> {

}
