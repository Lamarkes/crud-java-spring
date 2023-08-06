package com.example.crud.domain.product;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "product")
@Entity(name = "product")
@Getter // substitui os gets
@Setter // substitui os sets
@AllArgsConstructor // substitui o construtor com argumentos
@NoArgsConstructor // substitui o construtor sem argumentos
@EqualsAndHashCode(of = "id") // substitui os metodos equals e hashcode
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Number price_in_cents;
    private Boolean active;

    public Product(RequestProduct requestProduct){
        this.name = requestProduct.name();
        this.price_in_cents = requestProduct.price_in_cents();
        this.active = true;
    }
}
