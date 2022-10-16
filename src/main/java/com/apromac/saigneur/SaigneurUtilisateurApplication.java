package com.apromac.saigneur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
//@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients("com.apromac.saigneur")
public class SaigneurUtilisateurApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaigneurUtilisateurApplication.class, args);
	}

}
