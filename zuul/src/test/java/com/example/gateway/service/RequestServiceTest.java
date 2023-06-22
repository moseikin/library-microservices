package com.example.gateway.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
// toDO тесты написаны неправильно
@ExtendWith(MockitoExtension.class)
class RequestServiceTest {

    @Test
    void getToken() {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8083" + "/token/getToken")
                .queryParam("login", "{login}")
                .queryParam("password", "{password}")
                .encode()
                .toUriString();
        ;

        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("X-Forwarded-For", "192.168.43.248");
        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        Map<String, String> pathVars = new LinkedHashMap<>();
        pathVars.put("login", "login");
        pathVars.put("password", "password");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> exchange;

        try {
            exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class, pathVars);
        } catch (HttpClientErrorException e) {
            exchange = new ResponseEntity<>(e.getStatusCode());
        }

//        String requestURI = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRequestURI();

//        String remoteAddr = request.getRemoteAddr();

        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}