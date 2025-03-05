package org.example.currency.controller.implementation;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.currency.controller.LoginController;
import org.example.currency.dto.JwtTokenDTO;
import org.example.currency.dto.LoginRequestDTO;
import org.example.currency.exception.UnauthorizedException;
import org.example.currency.security.JwtTokenProvider;
import org.example.currency.service.BlackListService;
import org.example.currency.service.UserDetailsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginControllerImpl implements LoginController {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsLoader userDetailsLoader;
    private final BlackListService blackListService;

    @Autowired
    public LoginControllerImpl(JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, UserDetailsLoader userDetailsLoader, BlackListService blackListService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userDetailsLoader = userDetailsLoader;
        this.blackListService = blackListService;
    }

    @Override
    @PostMapping("/authorization")
    public JwtTokenDTO createAuthToken(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        log.info("Creating JWT token");

        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getLogin(), loginRequestDTO.getPassword()));

        } catch (AuthenticationException e) {
            log.info(e.getMessage());
            throw new UnauthorizedException("Invalid login or password");
        }

        UserDetails userDetails = userDetailsLoader.loadUserByUsername(loginRequestDTO.getLogin());
        String token = jwtTokenProvider.generateToken(userDetails);
        log.info("Generated token: {}", token);
        return new JwtTokenDTO(token);
    }

    @Override
    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String authorization) {
        blackListService.addToBlacklist(authorization.substring(7));
    }
}
