package org.example.currency.service.implementation;

import org.example.currency.model.User;
import org.example.currency.repository.UserRepository;
import org.example.currency.security.config.CurrencyUserDetails;
import org.example.currency.service.UserDetailsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsLoaderImpl implements UserDetailsLoader {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsLoaderImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new CurrencyUserDetails(user);
    }
}
