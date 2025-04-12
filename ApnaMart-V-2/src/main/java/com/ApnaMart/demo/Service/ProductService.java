package com.ApnaMart.demo.Service;

import com.ApnaMart.demo.Model.CategoryType;
import com.ApnaMart.demo.Model.Product;
import com.ApnaMart.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product AddProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> ProductById(Long prodId) {
        return productRepository.findById(prodId);
    }

    public List<Product> allProduct() {
        return productRepository.findAll();
    }

    public void DeleteProductById(Long prodId) {
        try {
            if (productRepository.existsById(prodId)) {
                productRepository.deleteById(prodId);
            }
        } catch (Exception e) {
            throw new BadCredentialsException("product Id is Not Found");
        }
    }

    public List<Product> getProductType(CategoryType categoryType) {
        List<Product> productList = productRepository.findBytypeOfProduct(categoryType);
        return productList;
    }
}
