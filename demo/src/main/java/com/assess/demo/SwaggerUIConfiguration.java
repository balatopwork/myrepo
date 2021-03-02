package com.assess.demo;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerUIConfiguration {
	
	@Bean
	public Docket SwaggerConfiguration() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/**"))
				.apis(RequestHandlerSelectors.basePackage("com.assess"))
				.build()
				.apiInfo(apiDeatilsInfo());
		
		
	}
	
	
	private ApiInfo apiDeatilsInfo() {
		return new ApiInfo(
				"Person CRUD API", 
				"Sample REST API", 
				"1.0",
				"Free Web service",
				new springfox.documentation.service.Contact("Balamurali", "https:bala.com", "bala@gmail.com"),
				"API License", "https:bala.com",Collections.emptyList());
		
		
		
	}

}
