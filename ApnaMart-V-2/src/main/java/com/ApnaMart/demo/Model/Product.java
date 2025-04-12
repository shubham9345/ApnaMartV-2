package com.ApnaMart.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prod_id;
    private String imageUrl;
    private String title;
    private String description;
    private Long price;
    private Long discountPrice;
    private int qty;
    private double rating;
    @Enumerated(EnumType.STRING)
    private CategoryType typeOfProduct;
    @ManyToMany
    @JoinTable(
            name = "cart_product", // Name of the join table
            joinColumns = @JoinColumn(name = "product_id"), // Foreign key to the Cart entity
            inverseJoinColumns = @JoinColumn(name = "cart_id") // Foreign key to the Product entity
    )
    @JsonIgnore
    private List<Cart> carts ;
    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private List<Orders> order;
}
