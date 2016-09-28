package com.mywork.springboot1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
//public class Springboot1Application extends SpringBootServletInitializer{
public class Springboot1Application{

	public static void main(String[] args) {
		SpringApplication.run(Springboot1Application.class, args);
	}

//	public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(Springboot1Application.class);
//	}
}
