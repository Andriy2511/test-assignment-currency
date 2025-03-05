package org.example.currency.listener;

import lombok.extern.slf4j.Slf4j;
import org.example.currency.dto.RoleDTO;
import org.example.currency.dto.UserDTO;
import org.example.currency.exception.RoleNotFoundException;
import org.example.currency.model.Role;
import org.example.currency.service.RoleService;
import org.example.currency.service.UserService;
import org.example.currency.utils.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DefaultInitializationListener implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public DefaultInitializationListener(RoleService roleService, UserService userService, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initializeRoles();
        registerAdminIfAbsent();
    }

    private void initializeRoles() {
        addRoleByName(UserRole.ADMIN.getName());
        addRoleByName(UserRole.CUSTOMER.getName());
        addRoleByName(UserRole.MANAGER.getName());
    }

    private void addRoleByName(String name){
        RoleDTO roleDTO = getRoleByName(name);
        if (roleDTO == null) {
            roleService.addRole(RoleDTO.toDTO(new Role(name)));
        }
    }

    private void registerAdminIfAbsent(){
        RoleDTO role = roleService.findRoleByName(UserRole.ADMIN.getName());
        if(userService.getUsersByRole(role).isEmpty()) {
            UserDTO user = UserDTO.builder()
                    .login(UserRole.ADMIN.getName())
                    .password(UserRole.ADMIN.getName())
                    .roleDTO(role)
                    .build();

            userService.addUser(user);
        }
    }

    private RoleDTO getRoleByName(String name){
        try {
            return roleService.findRoleByName(name);
        } catch (RoleNotFoundException e) {
            log.warn("Role with name {} not found: ", name);
            return null;
        }
    }
}
