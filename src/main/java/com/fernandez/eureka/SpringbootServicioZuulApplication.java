package com.fernandez.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableZuulProxy
@CrossOrigin(origins = "*")
@SpringBootApplication
public class SpringbootServicioZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioZuulApplication.class, args);
	}
	
	 

}
