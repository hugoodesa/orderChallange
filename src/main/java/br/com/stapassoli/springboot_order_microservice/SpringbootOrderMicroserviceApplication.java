package br.com.stapassoli.springboot_order_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@SpringBootApplication
@EnableScheduling
public class SpringbootOrderMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootOrderMicroserviceApplication.class, args);
	}

}
