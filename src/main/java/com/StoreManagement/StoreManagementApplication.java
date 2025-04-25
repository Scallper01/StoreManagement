package com.StoreManagement;

import com.StoreManagement.dataAccess.*;
import com.StoreManagement.entities.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
			  Supplier supplier1 = Supplier.builder().name("APPLE").address("CALIFORNIA, USA").build();
			  Product product1 = Product.builder().supplier(supplier1).name("MACBOOK").price(1799.99).build();
			  productRepository.save(product1);

			  Inventory inventory1 = Inventory.builder().product(product1).quantity(7).build();
			  Warehouse warehouse1 = warehouseRepository.findById(1L).get();
			  inventory1.setWarehouse(warehouse1);
			  List<Inventory> inventories = new ArrayList<>();
			  inventories.add(inventory1);
			  warehouse1.setInventories(inventories);
			  warehouseRepository.save(warehouse1);
		  };
	}

	public static void main(String[] args) {
		SpringApplication.run(StoreManagementApplication.class, args);

	}

}
