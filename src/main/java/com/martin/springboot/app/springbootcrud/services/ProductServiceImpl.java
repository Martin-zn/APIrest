package com.martin.springboot.app.springbootcrud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.martin.springboot.app.springbootcrud.entities.Product;
import com.martin.springboot.app.springbootcrud.repositories.ProductRepository;

@Service
@Primary
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repositori;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {

        return (List<Product>) repositori.findAll();
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return repositori.findById(id);
    }
    @Transactional
    @Override
    public Product save(Product product) {
        return repositori.save(product);
    }
    @Transactional
    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> optionalProduct = repositori.findById(id);

        optionalProduct.ifPresent(p -> {
            repositori.delete(p);
        });
        return optionalProduct;
    }

    @Transactional
    @Override
    public Optional<Product> update(Long id, Product product) {

        Optional<Product> optionalProduct = repositori.findById(id);

        if(optionalProduct.isPresent()){
            Product productDB = optionalProduct.orElseThrow();

            productDB.setName(product.getName());
            productDB.setPrice(product.getPrice());
            productDB.setDescription(product.getDescription());
            
            return Optional.of(repositori.save(productDB));
        }

        return optionalProduct;
    }

    

}
