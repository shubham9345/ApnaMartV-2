package com.ApnaMart.demo.Controller;

import com.ApnaMart.demo.Model.CategoryType;
import com.ApnaMart.demo.Model.Product;
import com.ApnaMart.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/Add-Product")
    public Product createProduct(@RequestBody Product products) {
        return productService.AddProduct(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.ProductById(id).orElseThrow(() -> new UsernameNotFoundException("product not found with this Id"));
        return ResponseEntity.ok(product);
    }

    @GetMapping("/All-Product")
    public List<Product> getAllProduct() {
        return productService.allProduct();
    }

    @DeleteMapping("/Delete-ProductById/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            productService.DeleteProductById(id);
            return ResponseEntity.ok("Product deleted successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/product-category")
    public ResponseEntity<List<Product>> getBusinessesByCategoryType(@RequestParam CategoryType categoryType) {
        List<Product> product = productService.getProductType(categoryType);
        return ResponseEntity.ok(product);
    }
}
