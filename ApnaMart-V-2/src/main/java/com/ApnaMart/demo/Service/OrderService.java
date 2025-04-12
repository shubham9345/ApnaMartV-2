package com.ApnaMart.demo.Service;

import com.ApnaMart.demo.Model.Orders;
import com.ApnaMart.demo.Model.Product;
import com.ApnaMart.demo.Model.UserInfo;
import com.ApnaMart.demo.Repository.OrdersRepository;
import com.ApnaMart.demo.Repository.ProductRepository;
import com.ApnaMart.demo.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.ApnaMart.demo.Model.OrderStatus.PLACED;

@Service
public class OrderService {
    @Autowired
    private OrdersRepository orderRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private ProductRepository productRepository;

    public Orders orderByUserId(Long userId) {
        if (!userInfoRepository.existsById(userId)) {
            throw new RuntimeException("user is not found with Id - " + userId);
        }
        Optional<UserInfo> user = userInfoRepository.findById(userId);
        Orders order = user.get().getOrder();
        if (order == null) {
            Orders order1 = new Orders();
            order1.setUsers(user.get());
            Set<Product> allProduct = new HashSet<>();
            List<Product> product = user.get().getCart().getProduct();
            allProduct.addAll(product);
            order1.setProducts(allProduct);
            orderRepository.save(order1);
            return order1;
        }
        List<Product> allProduct = user.get().getCart().getProduct();
        Set<Product> existingProduct = order.getProducts();
        existingProduct.addAll(allProduct);
        order.setUsers(user.get());
        order.setProducts(existingProduct);
        order.setOrderTime(LocalDateTime.now());
        order.setStatus(PLACED);
        orderRepository.save(order);
        return order;
    }

    public Orders seeCartByUserId(Long userId) {
        Optional<UserInfo> user = userInfoRepository.findById(userId);
        return user.get().getOrder();
    }

    public String deleteProductFromOrder(Long prodId, Long userId) {
        if (!productRepository.existsById(prodId)) {
            throw new RuntimeException("product not found with Id - " + prodId);
        }
        Optional<Product> product = productRepository.findById(prodId);
        if (!userInfoRepository.existsById(userId)) {
            throw new RuntimeException("user not found with Id - " + userId);
        }
        Optional<UserInfo> user = userInfoRepository.findById(userId);
        Orders order = user.get().getOrder();
        Set<Product> products = order.getProducts();
        products.remove(product.get());
        order.setProducts(products);
        orderRepository.save(order);
        return "Your Order is cancel successfully";
    }
}
