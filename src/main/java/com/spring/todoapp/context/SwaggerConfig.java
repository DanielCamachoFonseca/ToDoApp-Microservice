/**
 * 
 */
package com.spring.todoapp.context;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Ingeniero Daniel Camacho Fonseca
 * 
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)  
		          .select()                                  
		          .apis(RequestHandlerSelectors.basePackage("com.spring.todoapp.controller"))              
		          .paths(PathSelectors.any())                          
		          .build()
		          .apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
	      return new ApiInfo(
	              "Todo API",
	              "la API REST de ToDo App.",
	              "V1",
	              "terms of service",
	              new Contact("Ingeniero Daniel Camacho", "www.somexyzteamcontact.com", "danielcamacho_97@hotmail.com"),
	              "License of API", "API license URL", Collections.emptyList());
	  }
	}