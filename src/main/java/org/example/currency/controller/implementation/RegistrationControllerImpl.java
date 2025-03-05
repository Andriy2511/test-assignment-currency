package org.example.currency.controller.implementation;

import jakarta.validation.Valid;
import org.example.currency.controller.RegistrationController;
import org.example.currency.dto.RoleDTO;
import org.example.currency.dto.UserDTO;
import org.example.currency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class RegistrationControllerImpl implements RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping
    public UserDTO register(@Valid @RequestBody UserDTO user) {
        RoleDTO roleDTO = RoleDTO.builder().name("CUSTOMER").build();

        return userService.addUser(user.toBuilder().roleDTO(roleDTO).build());
    }
}
