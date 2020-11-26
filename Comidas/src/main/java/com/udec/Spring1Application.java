package com.udec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.udec"}) 
public class Spring1Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Spring1Application.class, args);
	}
	
	//Cambio para commit nuevo x3
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Spring1Application.class);
	}
}
