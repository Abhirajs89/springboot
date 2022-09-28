package com.abhiraj.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //All requests should be authenticated
        http.authorizeRequests(auth -> {
            auth.anyRequest().authenticated();
        });

        //If not authenticated then show a login popup
        http.httpBasic(Customizer.withDefaults());

        //disable CSRF
        http.csrf().disable();

        return http.build();
    }
}
