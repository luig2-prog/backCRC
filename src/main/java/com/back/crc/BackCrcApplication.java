package com.back.crc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class BackCrcApplication {

	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BackCrcApplication.class);
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(BackCrcApplication.class, args);
	}

}
