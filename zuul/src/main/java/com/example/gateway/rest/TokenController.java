package com.example.gateway.rest;

import com.example.gateway.dto.AuthRequestDto;
import com.example.gateway.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {
    private final RequestService requestService;

    @PostMapping("/getToken")
    public String getToken(@RequestBody AuthRequestDto authRequestDto) {
        return requestService.createTokenRequest(authRequestDto);
    }
}
