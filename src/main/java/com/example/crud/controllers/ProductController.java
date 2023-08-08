package com.example.crud.controllers;

import com.example.crud.domain.product.Product;
import com.example.crud.domain.product.RequestProduct;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import com.example.crud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    @Transactional
    public ResponseEntity getAllProducts(){
        List<Product> products = productRepository.findAllByActiveTrue();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity getProductById(@PathVariable Long id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data){

        Product product = new Product(data);
        productRepository.save(product);
        return ResponseEntity.ok().build();

    }

    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody @Valid RequestProduct data){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(data.name());
            product.setPrice_in_cents(data.price_in_cents());
            product.setActive(data.active());

            return ResponseEntity.ok(product);
        }else {
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setActive(false);
            return ResponseEntity.noContent().build();
            //productRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException();
        }
    }

}
