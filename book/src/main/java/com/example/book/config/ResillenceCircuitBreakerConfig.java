package com.example.book.config;

import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ResillenceCircuitBreakerConfig {

//    @Bean
//    public TimeLimiterConfig timeLimiterConfig() {
//        return new TimeLimiterConfig.Builder()
//                .timeoutDuration(Duration.ofSeconds(1000))
//                .build();
//    }
}
