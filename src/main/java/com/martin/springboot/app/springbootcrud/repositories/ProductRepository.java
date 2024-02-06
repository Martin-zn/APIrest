package com.martin.springboot.app.springbootcrud.repositories;



import org.springframework.data.repository.CrudRepository;

import com.martin.springboot.app.springbootcrud.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
