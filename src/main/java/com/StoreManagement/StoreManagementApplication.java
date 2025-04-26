package com.StoreManagement;

import com.StoreManagement.Service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StoreManagementApplication {

	private IStoreService storeService;

	@Autowired
	public StoreManagementApplication(IStoreService storeService) {
		this.storeService = storeService;
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("############# Bienvenue dans votre App#########");
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(StoreManagementApplication.class, args);

	}

}