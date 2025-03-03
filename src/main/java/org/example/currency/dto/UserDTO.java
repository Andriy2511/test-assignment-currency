package org.example.currency.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.example.currency.model.User;

@Getter
@ToString
@Builder(toBuilder = true)
public class UserDTO {

    private Long id;

    @NotBlank(message = "The field cannot be void")
    private String login;

    @NotBlank(message = "The field cannot be void")
    private String password;

    private RoleDTO roleDTO;

    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .roleDTO(RoleDTO.toDTO(user.getRole()))
                .build();
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setRole(RoleDTO.toEntity(userDTO.getRoleDTO()));
        return user;
    }
}
