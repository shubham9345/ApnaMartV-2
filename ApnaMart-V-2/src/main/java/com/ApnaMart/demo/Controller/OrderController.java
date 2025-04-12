package com.ApnaMart.demo.Controller;

import com.ApnaMart.demo.Model.Orders;
import com.ApnaMart.demo.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/order/{userId}")
    public ResponseEntity<?> orderByUserId(@PathVariable Long userId) {
        Orders order = orderService.orderByUserId(userId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/order/{userId}")
    public ResponseEntity<?> cartByUserId(@PathVariable Long userId) {
        Orders orders = orderService.orderByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @DeleteMapping("/order/delete/{prodId}/{userId}")
    public String deleteProductFromOrder(@PathVariable Long prodId, @PathVariable Long userId) {
        orderService.deleteProductFromOrder(prodId, userId);
        return "Your Order is Cancel successfully";
    }
}
