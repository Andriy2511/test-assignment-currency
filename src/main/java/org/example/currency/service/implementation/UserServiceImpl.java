package org.example.currency.service.implementation;

import org.example.currency.dto.RoleDTO;
import org.example.currency.dto.UserDTO;
import org.example.currency.model.Role;
import org.example.currency.model.User;
import org.example.currency.repository.UserRepository;
import org.example.currency.service.RoleService;
import org.example.currency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        userDTO = userDTO.toBuilder().roleDTO(roleService.findRoleByName(userDTO.getRoleDTO().getName())).build();

        User user = UserDTO.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return userDTO;
    }

    @Override
    public List<UserDTO> getUsersByRole(RoleDTO roleDTO) {
        Role role = RoleDTO.toEntity(roleService.findRoleByName(roleDTO.getName()));

        List<User> users = userRepository.findByRole(role);
        return users.stream().map(UserDTO::toDTO).toList();
    }
}
