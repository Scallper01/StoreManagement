package com.StoreManagement.dataAccess;

import com.StoreManagement.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface customerRepository extends JpaRepository<Customer, Long> {
}
