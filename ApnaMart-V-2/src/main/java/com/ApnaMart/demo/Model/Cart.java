package com.ApnaMart.demo.Model;

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
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;
    @OneToOne
    @JoinColumn(name = "user_Id", nullable = false)
    private UserInfo user;
    @ManyToMany
    @JoinTable(
            name = "cart_product", // Name of the join table
            joinColumns = @JoinColumn(name = "cart_id"), // Foreign key to the Cart entity
            inverseJoinColumns = @JoinColumn(name = "product_id") // Foreign key to the Product entity
    )
    private List<Product> product;
    private int noOfItems;
    @OneToOne(mappedBy = "carts", cascade = CascadeType.ALL)
    private BillDetails billDetails;
}
