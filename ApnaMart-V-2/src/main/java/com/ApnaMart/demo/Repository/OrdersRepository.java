package com.ApnaMart.demo.Repository;

import com.ApnaMart.demo.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders,Long> {
}
