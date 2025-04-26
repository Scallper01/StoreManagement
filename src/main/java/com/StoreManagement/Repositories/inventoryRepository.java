package com.StoreManagement.Repositories;

import com.StoreManagement.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface inventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByProduct_IdAndWarehouse_Id(Long id, Long id1);
}
