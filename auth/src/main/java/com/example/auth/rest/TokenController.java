package com.example.auth.rest;

import com.example.auth.dto.UserDto;
import com.example.auth.service.JwtProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {
    private final JwtProviderService jwtProviderService;

    @GetMapping("/getToken")
    public String getToken(@RequestParam String login, @RequestParam String password) {
        return jwtProviderService.getToken(login, password);
    }

    @GetMapping("/getUser")
    public UserDto getUsernamePasswordAuthToken(@RequestParam String token) {
        return jwtProviderService.getUserFromToken(token);
    }
}
