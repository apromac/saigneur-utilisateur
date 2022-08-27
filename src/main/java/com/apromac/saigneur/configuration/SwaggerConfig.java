package com.apromac.saigneur.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.apromac.saigneur.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "APROMAC API REST  * SAIGNEUR-UTILISATEUR",
                "La mise en place de cette API permet d’opérer une séparation des responsabilités entre le client et le serveur.\n" +
                        " Cette séparation permet donc une portabilité et évolutivité grandement améliorées. Cette API regroupe un\n" +
                        " ensemble de méthode pour obtenir un resultat.",
                "API TOS",
                "Terms of service",
                new Contact("apromac", "www.apromac.ci", "abraham.tiene@apromac.ci"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
