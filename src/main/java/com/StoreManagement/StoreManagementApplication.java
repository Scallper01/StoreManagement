package com.StoreManagement;

import com.StoreManagement.dataAccess.*;
import com.StoreManagement.entities.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StoreManagementApplication {


	@Bean
	CommandLineRunner commandLineRunner(customerRepository customerRepository,
										inventoryRepository inventoryRepository,
										orderContentRepository orderContentRepository,
										orderRepository orderRepository,
										productRepository productRepository,
										supplierRepository supplierRepository,
										warehouseRepository warehouseRepository) {
		  return args -> {
			  Supplier supplier = new Supplier();
		  };
	}

	public static void main(String[] args) {
		SpringApplication.run(StoreManagementApplication.class, args);

	}

}
