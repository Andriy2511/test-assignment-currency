package org.example.currency.controller;

import jakarta.validation.Valid;
import org.example.currency.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface RegistrationController {
    @GetMapping
    UserDTO register(@Valid @RequestBody UserDTO user);
}
