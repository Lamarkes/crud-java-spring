package com.example.crud.controllers;

import com.example.crud.domain.product.Product;
import com.example.crud.domain.product.RequestProduct;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import com.example.crud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity getAllProducts(){
        var products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data){

        Product product = new Product(data);
        productRepository.save(product);
        return ResponseEntity.ok().build();

    }

    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody @Valid RequestProduct data){
        Optional<Product> Optionalproduct = productRepository.findById(id);

        Product product = Optionalproduct.get();
        product.setName(data.name());
        product.setPrice_in_cents(data.price_in_cents());

        return ResponseEntity.ok(product);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id){

        productRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
