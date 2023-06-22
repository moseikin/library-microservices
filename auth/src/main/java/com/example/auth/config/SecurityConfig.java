package com.example.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final TestFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //toDo разобраться. Не должно быть отключено
                .csrf().disable()
                .formLogin().disable()
                .logout().disable()
                .authorizeRequests()
//                .antMatchers("/**").hasIpAddress("192.168.43.248")
                .and()
        .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }
}
