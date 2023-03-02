package com.andersenlab.cities.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 *SwaggerConfig
 *
 *@author Aliaksei Tumilovich
 *06.03.2023
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
           .select()
           .apis(RequestHandlerSelectors.any())
           .paths(PathSelectors.any())
           .build();
    }

}
