package com.StoreManagement.dataAccess;

import com.StoreManagement.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface supplierRepository extends JpaRepository<Supplier, Long> {
}
