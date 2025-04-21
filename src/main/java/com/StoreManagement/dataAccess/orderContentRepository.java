package com.StoreManagement.dataAccess;

import com.StoreManagement.entities.OrderContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface orderContentRepository extends JpaRepository<OrderContent, Long> {
}
