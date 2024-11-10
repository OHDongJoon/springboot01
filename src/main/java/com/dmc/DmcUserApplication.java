package com.dmc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(
        info = @Info(
                title = "API",
                description = "API Documentation",
                version ="v1",
                contact = @Contact(
                        name = "Oh Dong Jun",
                        email = "kok3443@naver.com"
                ),
                license = @License(
                        name = "Apache Tomcat/10.1.24 , MariaDB 10.5.12 , Java 17 , SpringBoot 3.3.0 , thymeleaf ,  QueryDSL , JPA",
                        url = ""
                )
        )
)
@SpringBootApplication
public class DmcUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(DmcUserApplication.class, args);
    }

}
