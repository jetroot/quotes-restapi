package com.springboot.redtest.config.beans;

import com.springboot.redtest.common.SpringApplicationContext;
import com.springboot.redtest.custom.CustomUserDetails;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    /**
     * Create spring application context for any object
     * @return
     */
    @Bean
    public SpringApplicationContext springApplicationContext() {
        return new SpringApplicationContext();
    }

    /**
     * Used in transferring data between objects
     * @return
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }

    /**
     * Config for Custom User Details
     * @return
     */
    @Bean
    public CustomUserDetails customUserDetails() {
        return new CustomUserDetails();
    }

//    /**
//     * Enable cors globally for spring
//     * @return
//     */
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.
//                        addMapping("/**")
//                        .allowedMethods("*")
//                        .allowedOrigins("http://localhost:8080");
//            }
//        };
//    }

}
