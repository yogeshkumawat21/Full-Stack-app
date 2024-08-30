package com.App.Yogesh.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class jwtValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader(jwtConstant.JWT_HEADER);

        if (jwt != null && jwt.startsWith(jwtConstant.TOKEN_PREFIX)) {
            try {
                // Remove "Bearer " prefix
                String token = jwt.substring(jwtConstant.TOKEN_PREFIX.length());

                // Extract email and roles/authorities from the JWT
                String email = JwtProvider.getEmailFromJwtToken(token);
                List<GrantedAuthority> authorities = JwtProvider.getAuthoritiesFromJwtToken(token);

                // Create an authentication token
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);

                // Set the authentication context
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                // Log the exception for debugging
                System.err.println("JWT validation failed: " + e.getMessage());
                throw new BadCredentialsException("Invalid Token", e);
            }
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }
}
