package br.com.ifsp.garageando;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

@SpringBootApplication
@EnableCaching
public class GarageandoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GarageandoApplication.class, args);
	}

}
