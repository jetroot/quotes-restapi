package com.springboot.redtest.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Cors configuration globally
 */
@Configuration
public class CorsConfig {

    /**
     * Enable cors for specific for frontend domain
     * @return
     */
    @Bean
    public WebMvcConfigurer CORSConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.
                        addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("http://localhost:8080");
            }
        };
    }

}
