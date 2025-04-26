package com.StoreManagement.Repositories;

import com.StoreManagement.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface supplierRepository extends JpaRepository<Supplier, Long> {

}
