package com.example.sprinprojet;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
@SpringBootApplication
@EnableScheduling

public class SprinprojetApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprinprojetApplication.class, args);
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Configuration
    public class CorsConfig {

        @Bean
        public CorsFilter corsFilter() {
            CorsConfiguration corsConfig = new CorsConfiguration();

            // Allow all origins (you might want to restrict this in production)
            corsConfig.addAllowedOrigin("http://localhost:4200/");

            // Allow specific HTTP methods
            corsConfig.addAllowedMethod("GET");
            corsConfig.addAllowedMethod("POST");
            corsConfig.addAllowedMethod("PUT");
            corsConfig.addAllowedMethod("DELETE");

            // Allow all headers
            corsConfig.addAllowedHeader("*");

            // Set the maximum age of the preflight request in seconds
            corsConfig.setMaxAge(3600L); // 1 hour

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

            // Apply the CORS configuration to all paths
            source.registerCorsConfiguration("/**", corsConfig);

            return new CorsFilter(source);
        }
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
