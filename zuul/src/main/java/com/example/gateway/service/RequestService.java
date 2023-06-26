package com.example.gateway.service;

import com.example.gateway.dto.AuthRequestDto;
import com.example.gateway.dto.UserDto;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestService {
    private static final String AUTH_APP_NAME = "auth";
    private static final String GET_TOKEN_PATH = "token/getToken";
    private static final String GET_USER_PATH = "token/getUser";

    private final EurekaClient eurekaClient;

    /**
     * Пример работы Resilence Retry.
     * В конфиге переопределены параметры:
     *         maxAttempts: 3 - максимальное количество попыток
     *         waitDuration: 2s - время ожидания между попытками
     * imitateServiceFailures в случайном порядке имитирует ошибки сервиса. При этом Retry до 3 раз
     * с периодичностью в 2 секунды будет пытаться до него достучаться.
     * Если все три раза не получится, то будет вызван метод, указанный в параметре fallbackMethod
     */
    @Retry(name = "default", fallbackMethod = "retryMethod")
    public String createTokenRequest(AuthRequestDto authRequestDto) {
        imitateServiceFailures();

        String homePageUrl = getAuthServiceHomePageUrl();

        String url = UriComponentsBuilder.fromHttpUrl(homePageUrl + GET_TOKEN_PATH)
                .queryParam("login", "{login}")
                .queryParam("password", "{password}")
                .encode()
                .toUriString();

        HttpEntity<?> httpEntity = createHttpEntity();

        Map<String, String> pathVars = new LinkedHashMap<>();
        pathVars.put("login", authRequestDto.getLogin());
        pathVars.put("password", authRequestDto.getPassword());

        return new RestTemplate().exchange(url, HttpMethod.GET, httpEntity, String.class, pathVars).getBody();
    }

    private void imitateServiceFailures() {
        if (RandomUtils.nextBoolean()) {
            log.info("Ошибка. Должен сработать RETRY");
            throw new IllegalStateException();
        } else {
            log.info("Ошибки нету");
        }
    }

    public String retryMethod(Throwable t) {
        log.info("Этот метод вызвался, когда все попытки не увенчались успехом");
        return "Этот метод вызвался, когда все попытки не увенчались успехом";
    }

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
