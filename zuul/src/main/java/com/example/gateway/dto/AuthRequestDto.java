package com.example.gateway.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AuthRequestDto {
    private final String login;
    private final String password;
}
