package com.mywork.springboot1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class Springboot1Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot1Application.class, args);
	}
}
