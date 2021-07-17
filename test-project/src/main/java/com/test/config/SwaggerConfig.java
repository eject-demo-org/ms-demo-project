package com.test.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket testApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.test.controller"))
				.paths(regex("/test/getCopyModel.*"))
				//.paths(PathSelectors.any())
				.build()
				.apiInfo(metaData());
	}


	private ApiInfo metaData() {		
		return new ApiInfoBuilder()
				.title("Test API")
				.description("Test Project")
				.version("1.0")
				.termsOfServiceUrl("Terms and Conditions: No copying")
				.contact(new Contact("Neelesh", "no url", "testmail@neelesh.com"))
				.license("No License")
				.build();
	}


}
