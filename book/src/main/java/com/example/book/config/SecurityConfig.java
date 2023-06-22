package com.example.book.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//                .cors()
//                .and()
//                .authorizeRequests()
//                .mvcMatchers().hasIpAddress("127.0.0.2")
//                .and()
                .authorizeHttpRequests()
                .antMatchers("/getToken").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/")
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins("http://localhost:8080");
//            }
//        };
//    }
//
//    private static AuthorizationManager<RequestAuthorizationContext> hasIpAddress(String ipAddress) {
//        IpAddressMatcher ipAddressMatcher = new IpAddressMatcher(ipAddress);
//        return (authentication, context) -> {
//            HttpServletRequest request = context.getRequest();
//            return new AuthorizationDecision(ipAddressMatcher.matches(request));
//        };
//
//    }


}
