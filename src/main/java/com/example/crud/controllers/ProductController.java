package com.example.crud.controllers;

import com.example.crud.domain.product.Product;
import com.example.crud.domain.product.RequestProduct;
import jakarta.validation.Valid;
import com.example.crud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct data){
        Product product = productRepository.getReferenceById(data.id());
        product.setName(data.name());
        product.setPrice_in_cents(data.price_in_cents());

        return ResponseEntity.ok();

    }

}
