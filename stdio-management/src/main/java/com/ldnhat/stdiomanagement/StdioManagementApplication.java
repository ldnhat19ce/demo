package com.ldnhat.stdiomanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@SpringBootApplication
public class StdioManagementApplication {

	@Bean
	public InternalResourceViewResolver defaultViewResolver(){
		return new InternalResourceViewResolver();
	}

	public static void main(String[] args) {
		SpringApplication.run(StdioManagementApplication.class, args);
	}

}
