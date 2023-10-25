package com.harry.JwtRoleBaseAuthorization.security.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harry.JwtRoleBaseAuthorization.security.user_principal.UserDetailsPrincipal;
import com.harry.JwtRoleBaseAuthorization.service.IJwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private IJwtService jwtService;

    @Autowired
    private UserDetailsPrincipal detailsPrincipal;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (isAuthRequest(request))
            filterChain.doFilter(request, response);
        else {

            if (hasContainAuthHeader(request)) {

                try {
                    String token = getTokenFromHeader(request);

                    String username = jwtService.validateToken(token);

                    UserDetails userDetails = detailsPrincipal.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    filterChain.doFilter(request, response);

                } catch (Exception e) {
                    // write error to header
                    response.setHeader("error", e.getMessage());
                    response.setStatus(HttpStatus.FORBIDDEN.value());

                    ResponseEntity.badRequest().body(e.getMessage());

                    // write error to the response body
                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", e.getMessage());
                    response.setContentType("application/json");

                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }

            } else {
                filterChain.doFilter(request, response);
            }

        }
    }

    private boolean isAuthRequest(HttpServletRequest request) {
        return request.getServletPath().equals("/api/v1/auth/login");
    }

    private boolean hasContainAuthHeader(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        return authHeader != null && authHeader.startsWith("Bearer ");
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        return request.getHeader("Authorization").substring("Bearer ".length()).trim();
    }
}