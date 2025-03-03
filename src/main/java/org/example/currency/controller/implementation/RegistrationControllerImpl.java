package org.example.currency.controller.implementation;

import jakarta.validation.Valid;
import org.example.currency.controller.RegistrationController;
import org.example.currency.dto.UserDTO;
import org.example.currency.model.Role;
import org.example.currency.service.RoleService;
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
    private final RoleService roleService;

    @Autowired
    public RegistrationControllerImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public UserDTO register(@Valid @RequestBody UserDTO user) {
        user.toBuilder().roleDTO(roleService.findRoleByName("CUSTOMER")); //role to enum
        return userService.addUser(user);
    }
}
