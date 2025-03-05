package org.example.currency.controller.implementation;

import jakarta.validation.Valid;
import org.example.currency.controller.AdminController;
import org.example.currency.dto.RoleDTO;
import org.example.currency.dto.UserDTO;
import org.example.currency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminControllerImpl implements AdminController {

    private final UserService userService;

    @Autowired
    public AdminControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping("/manager")
    public void addManager(@Valid @RequestBody  UserDTO userDTO){
        RoleDTO roleDTO = RoleDTO.builder().name("CUSTOMER").build();
        userDTO.toBuilder().roleDTO(roleDTO);
        userService.addUser(userDTO);
    }
}
