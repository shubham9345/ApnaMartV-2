package com.ApnaMart.demo.Service;

import com.ApnaMart.demo.Model.Cart;
import com.ApnaMart.demo.Model.Product;
import com.ApnaMart.demo.Model.UserInfo;
import com.ApnaMart.demo.Repository.CartRepository;
import com.ApnaMart.demo.Repository.ProductRepository;
import com.ApnaMart.demo.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;

    public Cart addProductByUserId(Long prodId, Long userId) {
        if (!productRepository.existsById(prodId)) {
            throw new RuntimeException("product not found with Id - " + prodId);
        }
        Optional<Product> product = productRepository.findById(prodId);
        if (!userInfoRepository.existsById(userId)) {
            throw new RuntimeException("user not found with Id - " + userId);
        }


        Optional<UserInfo> user = userInfoRepository.findById(userId);
        Cart cart = user.get().getCart();

        if (cart == null) {
            Cart cart1 = new Cart();
            cart1.setUser(user.get());
            List<Product> allProducts = new ArrayList<>();
            allProducts.add(product.get());
            cart1.setProduct(allProducts);
            cart1.setNoOfItems(allProducts.size());
            cartRepository.save(cart1);
            return cart1;
        }
        List<Product> allProduct = cart.getProduct();
        allProduct.add(product.get());
        cart.setUser(user.get());
        cart.setProduct(allProduct);
        cart.setNoOfItems(allProduct.size());
        cartRepository.save(cart);
        return cart;
    }

    public Cart seeCartByUserId(Long userId) {
        Optional<UserInfo> user = userInfoRepository.findById(userId);
        return user.get().getCart();
    }

    public String deleteProductFromCart(Long prodId, Long userId) {
        if (!productRepository.existsById(prodId)) {
            throw new RuntimeException("product not found with Id - " + prodId);
        }
        Optional<Product> product = productRepository.findById(prodId);
        if (!userInfoRepository.existsById(userId)) {
            throw new RuntimeException("user not found with Id - " + userId);
        }
        Optional<UserInfo> user = userInfoRepository.findById(userId);
        Cart cart = user.get().getCart();
        List<Product> products = cart.getProduct();
        products.remove(product.get());
        cart.setProduct(products);
        cartRepository.save(cart);
        return "product is removed from cart sucessfully";
    }
}
