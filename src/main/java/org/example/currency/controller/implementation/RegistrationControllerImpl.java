package org.example.currency.controller.implementation;

import jakarta.validation.Valid;
import org.example.currency.controller.RegistrationController;
import org.example.currency.dto.RoleDTO;
import org.example.currency.dto.UserDTO;
import org.example.currency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationControllerImpl implements RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping
    public UserDTO register(@Valid @RequestBody UserDTO user) {
        RoleDTO roleDTO = RoleDTO.builder().name("CUSTOMER").build();
        user.toBuilder().roleDTO(roleDTO);
        return userService.addUser(user);
    }
}
