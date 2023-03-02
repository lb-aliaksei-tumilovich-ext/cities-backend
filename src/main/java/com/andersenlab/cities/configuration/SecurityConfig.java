package com.andersenlab.cities.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *SecurityConfig
 *
 *@author Aliaksei Tumilovich
 *01.03.2023
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    public static final String[] ALLOWED_ORIGINS = { "http://localhost:4200" };
    public static final String[] ALLOWED_METHODS = { "PUT", "GET", "OPTIONS" };
    public static final int MAX_AGE = 3600;
    private static final String[] AUTH_WHITE_LIST = {
       "/v3/api-docs/**",
       "/swagger-ui/**",
       "/v2/api-docs/**",
       "/swagger-resources/**"
    };
    @Value("${cities.password}")
    private String password;
    @Value("${cities.username}")
    private String username;
    @Value("${cities.role}")
    private String role;

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
           .username(username)
           .password(password)
           .roles(role)
           .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
           .csrf()
           .disable()
           .cors()
           .and()
           .authorizeHttpRequests()
           .requestMatchers(AUTH_WHITE_LIST).permitAll()
           .requestMatchers("/**").authenticated()
           .and()
           .httpBasic();
        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsMappingConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                   .allowedMethods(ALLOWED_METHODS)
                   .allowedOrigins(ALLOWED_ORIGINS)
                   .maxAge(MAX_AGE);
            }
        };
    }
}
