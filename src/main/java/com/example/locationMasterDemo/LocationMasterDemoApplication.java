package com.example.locationMasterDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.example.locationMasterDemo.service",
							"com.example.locationMasterDemo.config" ,
									"com.example.locationMasterDemo.controller" ,
											"com.example.locationMasterDemo.serializer"})
@EnableAutoConfiguration
@SpringBootApplication
public class LocationMasterDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationMasterDemoApplication.class, args);
	}

}
