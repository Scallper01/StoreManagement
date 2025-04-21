package com.StoreManagement.dataAccess;

import com.StoreManagement.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface inventoryRepository extends JpaRepository<Inventory, Long> {
}
