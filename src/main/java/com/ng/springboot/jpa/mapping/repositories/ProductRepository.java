package com.ng.springboot.jpa.mapping.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ng.springboot.jpa.mapping.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
