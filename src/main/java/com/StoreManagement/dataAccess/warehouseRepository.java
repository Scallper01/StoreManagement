package com.StoreManagement.dataAccess;

import com.StoreManagement.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface warehouseRepository extends JpaRepository<Warehouse, Long> {
}
