package com.metronics.jobservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MetronicsJobServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetronicsJobServiceApplication.class, args);
	}

}
