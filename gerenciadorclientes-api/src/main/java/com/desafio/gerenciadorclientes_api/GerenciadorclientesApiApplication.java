package com.desafio.gerenciadorclientes_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class GerenciadorclientesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorclientesApiApplication.class, args);
	}

}
