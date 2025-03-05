package org.example.currency.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.currency.service.BlackListService;
import org.example.currency.service.UserDetailsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

import java.io.IOException;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final BlackListService blackListService;
    private final UserDetailsLoader userDetailsLoader;

    @Autowired
    public JwtRequestFilter(JwtTokenProvider jwtTokenProvider, BlackListService blackListService, UserDetailsLoader userDetailsLoader) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.blackListService = blackListService;
        this.userDetailsLoader = userDetailsLoader;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String login = null;
        String jwtToken;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);
            if(!blackListService.isBlacklisted(jwtToken)) {
                try {
                    login = jwtTokenProvider.getUsernameFromToken(jwtToken);
                } catch (ExpiredJwtException | SignatureException e){
                    handleJwtException(response, e);
                    return;
                }
            } else {
                log.debug("JWT token in the blacklist");
                setResponseErrorMessage(response, "JWT token in the blacklist");
                return;
            }
        }

        setAuthenticationForUser(login);
        filterChain.doFilter(request, response);
    }

    private void setResponseErrorMessage(HttpServletResponse response, String message) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, message);
    }

    private void setAuthenticationForUser(String username){
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsLoader.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }


    private void handleJwtException(HttpServletResponse response, Exception e) throws IOException {
        String message = e instanceof ExpiredJwtException ? "Expired or invalid JWT token" : "The JWT token is invalid";
        log.debug(message);
        setResponseErrorMessage(response, message);
    }
}
