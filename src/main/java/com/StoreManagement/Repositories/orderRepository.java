package com.StoreManagement.Repositories;

import com.StoreManagement.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface orderRepository extends JpaRepository<Order, Long> {
}
