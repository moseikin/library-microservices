package com.example.book.service;

import com.example.book.dto.UserDto;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RequestService {
    private static final String AUTH_APP_NAME = "auth";
    private static final String GET_USER_PATH = "token/getUser";

    private final EurekaClient eurekaClient;

    public UserDto createUserRequest(String token) {
        String homePageUrl = getAuthServiceHomePageUrl();

        String url = UriComponentsBuilder.fromHttpUrl(homePageUrl + GET_USER_PATH)
                .queryParam("token", "{token}")
                .encode()
                .toUriString();
        Map<String, String> pathVars = new LinkedHashMap<>();
        pathVars.put("token", token);

        HttpEntity<?> httpEntity = createHttpEntity();

        return new RestTemplate().exchange(url, HttpMethod.GET, httpEntity, UserDto.class, pathVars).getBody();
    }

    private String getAuthServiceHomePageUrl() {
        Application app = eurekaClient.getApplication(AUTH_APP_NAME);
        return app.getInstances().get(0).getHomePageUrl();
    }

    private HttpEntity<?> createHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(httpHeaders);
    }
}
