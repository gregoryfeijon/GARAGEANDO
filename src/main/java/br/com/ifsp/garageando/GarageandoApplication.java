package br.com.ifsp.garageando;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GarageandoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GarageandoApplication.class, args);
	}

}
