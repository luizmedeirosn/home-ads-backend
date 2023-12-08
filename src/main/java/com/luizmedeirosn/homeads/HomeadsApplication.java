package com.luizmedeirosn.homeads;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HomeadsApplication {

	public static void main(String[] args) {
		try {
			new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}

		SpringApplication.run(HomeadsApplication.class, args);
	}

}
