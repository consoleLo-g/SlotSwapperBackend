package com.example.SlotSwapperBackend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Allow your frontend
        config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));

        // Allow credentials (JWT in Authorization header)
        config.setAllowCredentials(true);

        // Allow all headers
        config.addAllowedHeader("*");

        // Allow all HTTP methods
        config.addAllowedMethod("*");

        // Preflight cache
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
