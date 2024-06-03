package com.arsatapathy.springbootsecurityjwtdemo.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebMvcConfigurer extends WebMvcConfigurerAdapter {

    // Override methods to customize Spring MVC configuration
    // Example:
    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    //     registry.addMapping("/**").allowedOrigins("*");
    // }
}