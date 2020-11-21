package com.udec.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)				
				.apiInfo(usersApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.udec"))
				.paths(PathSelectors.any()).build();
	}
	
	private ApiInfo usersApiInfo() {
        return new ApiInfoBuilder()
                .title("Comidas rápidas y ricas")
                .version("1.0")
                .license("Apache License Version 2.0")
                .build();
    }

}