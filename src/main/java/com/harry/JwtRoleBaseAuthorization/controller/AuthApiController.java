package com.harry.JwtRoleBaseAuthorization.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harry.JwtRoleBaseAuthorization.dto.request.LoginRequestDTO;
import com.harry.JwtRoleBaseAuthorization.dto.request.RegistrationRequestDTO;
import com.harry.JwtRoleBaseAuthorization.dto.response.UserResponseDTO;
import com.harry.JwtRoleBaseAuthorization.exception.AuthenticatedFailureException;
import com.harry.JwtRoleBaseAuthorization.service.IAuthService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthApiController {

    @Autowired
    private IAuthService authService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto) {
        if (dto.getUsername().isEmpty() || dto.getPassword().isEmpty())
            throw new AuthenticatedFailureException("Empty username or password!");

        return ResponseEntity.ok().body(authService.login(dto));
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequestDTO dto) {
        // URI uri = URI
        // .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/auth/register").toUriString());
        try {
            return new ResponseEntity<UserResponseDTO>(authService.register(dto).get(), HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error_message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, Object> body) {
        return ResponseEntity.ok().body(authService.refreshToken(body));
    }
}