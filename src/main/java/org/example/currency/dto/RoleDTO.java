package org.example.currency.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.example.currency.model.Role;

@Getter
@ToString
@Builder(toBuilder = true)
public class RoleDTO {

    private Long id;

    @NotBlank(message = "The field cannot be void")
    private String name;

    public static RoleDTO toDTO(Role role) {
        return RoleDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public static Role toEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        return role;
    }
}
