package com.harry.JwtRoleBaseAuthorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harry.JwtRoleBaseAuthorization.dto.request.LoginRequestDTO;
import com.harry.JwtRoleBaseAuthorization.service.IAuthService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthApiController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok().body(authService.login(dto));
    }

}
