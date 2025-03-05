package org.example.currency.security.config;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.currency.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CurrencyUserDetails implements UserDetails {

    private String login;
    private String password;
    private String role;

    public CurrencyUserDetails(User user) {
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.role = getUserRole(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //TODO
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    private String getUserRole(User user){
        return user.getRole().getName();
    }
}
