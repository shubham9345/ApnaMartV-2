package com.ApnaMart.demo.Repository;

import com.ApnaMart.demo.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
