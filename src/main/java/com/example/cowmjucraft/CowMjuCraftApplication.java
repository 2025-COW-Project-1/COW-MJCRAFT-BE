package com.example.cowmjucraft;

import com.example.cowmjucraft.global.config.AdminInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class CowMjuCraftApplication {

	private final AdminInitializer adminInitializer;

	public CowMjuCraftApplication(AdminInitializer adminInitializer) {
		this.adminInitializer = adminInitializer;
	}

	public static void main(String[] args) {
		SpringApplication.run(CowMjuCraftApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void seedAdmin() {
		adminInitializer.seedAdminIfNecessary();
	}

}
