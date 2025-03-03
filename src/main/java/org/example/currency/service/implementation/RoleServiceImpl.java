package org.example.currency.service.implementation;

import org.example.currency.dto.RoleDTO;
import org.example.currency.exception.RoleNotFoundException;
import org.example.currency.model.Role;
import org.example.currency.repository.RoleRepository;
import org.example.currency.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void addRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        roleRepository.save(role);
    }

    @Override
    public RoleDTO findRoleByName(String name) {
        return RoleDTO
                .toDTO(roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Role not found")));
    }
}
