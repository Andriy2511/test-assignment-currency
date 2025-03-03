package org.example.currency.service;

import org.example.currency.dto.RoleDTO;

public interface RoleService {
    void addRole(RoleDTO roleDTO);

    RoleDTO findRoleByName(String name);
}
