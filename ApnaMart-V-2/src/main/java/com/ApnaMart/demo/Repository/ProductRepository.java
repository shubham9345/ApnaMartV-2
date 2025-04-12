package com.ApnaMart.demo.Repository;

import com.ApnaMart.demo.Model.CategoryType;
import com.ApnaMart.demo.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findBytypeOfProduct(CategoryType categoryType);
}
