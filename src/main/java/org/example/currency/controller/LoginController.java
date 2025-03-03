package org.example.currency.controller;

import jakarta.validation.Valid;
import org.example.currency.dto.JwtTokenDTO;
import org.example.currency.dto.LoginRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface LoginController {
    @PostMapping
    JwtTokenDTO createAuthToken(@Valid @RequestBody LoginRequestDTO loginRequestDTO);

    @PostMapping("/logout")
    void logout(@RequestHeader("Authorization") String authorization);
}
