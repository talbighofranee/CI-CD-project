package com.example.sprinprojet.configuration;

import io.swagger.v3.oas.models.OpenAPI;


import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI openApiInformation(){
        Contact contact =new Contact().email("talbi.ghofrane@esprittn").name("ghofrane talbi");
        Info info =new Info().contact(contact).description("")
                .summary("Manage application")
                .title("study").version("1.0.0")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));
        return new OpenAPI().info(info);
    }


    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://192.168.80.128:4200") // Replace with your frontend URL
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowedHeaders("*");
        }
    }

}
