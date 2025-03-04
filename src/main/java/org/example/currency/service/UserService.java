package org.example.currency.service;

import org.example.currency.dto.RoleDTO;
import org.example.currency.dto.UserDTO;
import org.example.currency.model.User;

import java.util.List;

public interface UserService {

    UserDTO addUser(UserDTO user);

    List<UserDTO> getUsersByRole(RoleDTO roleDTO);

    User findUserByLogin(String username);
}
