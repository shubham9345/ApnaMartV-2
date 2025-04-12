package com.ApnaMart.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    @OneToOne
    @JoinColumn(name = "users_Id", nullable = false)
    private UserInfo users;
    @ManyToMany
    @JoinTable(
            name = "order_product", // Name of the join table
            joinColumns = @JoinColumn(name = "order_id"), // Foreign key to the Cart entity
            inverseJoinColumns = @JoinColumn(name = "products_id") // Foreign key to the Product entity
    )
    private Set<Product> products;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private LocalDateTime orderTime;
}
