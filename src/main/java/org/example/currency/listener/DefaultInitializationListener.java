package org.example.currency.listener;

import org.example.currency.dto.RoleDTO;
import org.example.currency.dto.UserDTO;
import org.example.currency.model.Role;
import org.example.currency.service.RoleService;
import org.example.currency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultInitializationListener implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleService roleService;
    private final UserService userService;
    private final String customerRole = "CUSTOMER";
    private final String adminRole = "ADMIN";
    private final String managerRole = "MANAGER";

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
        addRoleByName(adminRole);
        addRoleByName(customerRole);
        addRoleByName(managerRole);
    }

    private void addRoleByName(String name){
        if (roleService.findRoleByName(name) == null) {
            roleService.addRole(RoleDTO.toDTO(new Role(name)));
        }
    }

    private void registerAdminIfAbsent(){
        RoleDTO role = roleService.findRoleByName(adminRole);
        if(userService.getUsersByRole(role).isEmpty()) {
            UserDTO user = UserDTO.builder()
                    .login(adminRole)
                    .password(adminRole)
                    .roleDTO(role)
                    .build();

            userService.addUser(user);
        }
    }
}
