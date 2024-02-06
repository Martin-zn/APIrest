package com.martin.springboot.app.springbootcrud.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martin.springboot.app.springbootcrud.entities.Product;
import com.martin.springboot.app.springbootcrud.services.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


//Defino un controller con rest controller
//Defino una ruta obligatoria
@RestController
@RequestMapping("/api/product")
public class ProductController {
    //inyecto las dependencias de service
    @Autowired
    private ProductService service;

    //Genero un metodo

    //Muestra la lista completa
    @GetMapping("/list")
    public List<Product> list(){
        return service.findAll();
    }

    //metodo para buscar por id
    @GetMapping("/{id}")
    //Con la anotacion pathvariable digo que el argumento que ocupare en la url se ocupe como variable para la funcion
    public ResponseEntity<?> viewProduct(@PathVariable Long id){
        Optional<Product> productOptional = service.findById(id);

        if (productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> crear(@Valid @RequestBody Product product){

        Product newProduct = service.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product product){
        Optional<Product> optionalProduct = service.update(id, product);
        if (optionalProduct.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalProduct.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }
    

    //Product
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){

        Optional<Product> productOptional = service.delete(id);

        if (productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    

}
