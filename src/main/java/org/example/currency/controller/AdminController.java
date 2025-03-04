package org.example.currency.controller;

import org.example.currency.dto.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AdminController {
    @PostMapping("/manager")
    void addManager(@RequestBody UserDTO userDTO);
}
