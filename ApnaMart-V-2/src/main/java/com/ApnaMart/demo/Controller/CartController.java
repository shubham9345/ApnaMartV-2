package com.ApnaMart.demo.Controller;

import com.ApnaMart.demo.Model.Cart;
import com.ApnaMart.demo.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/cart/{prodId}/{userId}")
    public ResponseEntity<?> addCart(@PathVariable Long prodId, @PathVariable Long userId) {
        Cart carts = cartService.addProductByUserId(prodId, userId);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<?> cartByUserId(@PathVariable Long userId) {
        Cart cart = cartService.seeCartByUserId(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{prodId}/{userId}")
    public String deleteProductFromCart(@PathVariable Long prodId, @PathVariable Long userId) {
        cartService.deleteProductFromCart(prodId, userId);
        return "product from cart is removed successfully";
    }
}

