package com.metronics.metronicsmainservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.JpaRepository;

//@SpringBootApplication
@EnableDiscoveryClient
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableFeignClients
@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
		org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class}
		)
public class MetronicsMainServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetronicsMainServiceApplication.class, args);
	}

}
