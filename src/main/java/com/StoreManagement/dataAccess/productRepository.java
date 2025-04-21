package com.StoreManagement.dataAccess;

import com.StoreManagement.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepository extends JpaRepository<Product, Long> {
}
