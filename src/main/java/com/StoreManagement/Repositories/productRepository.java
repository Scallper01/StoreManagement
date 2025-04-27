package com.StoreManagement.Repositories;

import com.StoreManagement.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface productRepository extends JpaRepository<Product, Long> {
    List<Product> findBySupplier_Id(Long id);
}
