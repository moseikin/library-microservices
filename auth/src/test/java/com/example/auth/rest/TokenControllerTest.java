package com.example.auth.rest;

import com.example.auth.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
@Slf4j
class TokenControllerTest {
    Logger logger = Logger.getLogger("TokenControllerTest.class");

    @Autowired
    private HttpServletRequest request;

    @Test
    void getToken() {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8083" + "/token/getToken")
                .queryParam("login", "{login}")
                .queryParam("password", "{password}")
                .encode()
                .toUriString();;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Forwarded-For", "192.168.43.248");
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

        String requestURI = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRequestURI();

//        String remoteAddr = request.getRemoteAddr();

        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getToken_ShouldReturn403Status() {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8083" + "/token/getToken")
                .queryParam("login", "{login}")
                .queryParam("password", "{password}")
                .encode()
                .toUriString();;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Forwarded-For", "134.45.34.7");
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

        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void getUsernamePasswordAuthToken() {
    }
}