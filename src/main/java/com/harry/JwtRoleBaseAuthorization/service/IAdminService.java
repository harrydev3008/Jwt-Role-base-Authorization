package com.harry.JwtRoleBaseAuthorization.service;

import com.harry.JwtRoleBaseAuthorization.dto.response.UserResponseDTO;

public interface IAdminService {
    Iterable<UserResponseDTO> loadAllUsers();
}