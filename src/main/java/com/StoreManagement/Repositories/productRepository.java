package com.StoreManagement.Repositories;

import com.StoreManagement.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface productRepository extends JpaRepository<Product, Long> {
}
