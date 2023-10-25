package com.harry.JwtRoleBaseAuthorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harry.JwtRoleBaseAuthorization.service.IAdminService;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminApiController {

    @Autowired
    private IAdminService adminService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> loadUsers() {
        return ResponseEntity.ok().body(adminService.loadAllUsers());
    }

}
