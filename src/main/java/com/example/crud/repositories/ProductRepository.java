package com.example.crud.repositories;

import com.example.crud.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {


    Optional<Product> findById(Long id);

    List<Product> findAllByActiveTrue();

}
