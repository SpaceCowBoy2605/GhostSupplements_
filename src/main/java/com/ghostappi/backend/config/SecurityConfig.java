package com.ghostappi.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(requests -> {
                    requests
                            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                            .requestMatchers("/login", "/oauth2/**", "/login/oauth2/**").permitAll()
                            .requestMatchers("/products/**", "/wallets/**","/rewards/**","/points/**","/cards/**","/Coupon/**")
                            .hasRole("CLIENT")                                                                      
                            .anyRequest().authenticated(); 
                })
                .formLogin(form -> form.loginPage("/login").permitAll()) 
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("http://localhost:8080//doc/swagger-ui/index.html#", true)                                                  
                        .failureUrl("/login?error=true") 
                )
                .build();
    }



}


