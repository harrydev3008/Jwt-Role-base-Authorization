package com.harry.JwtRoleBaseAuthorization.service;

import com.harry.JwtRoleBaseAuthorization.dto.request.LoginRequestDTO;
import com.harry.JwtRoleBaseAuthorization.dto.request.RegistrationRequestDTO;
import com.harry.JwtRoleBaseAuthorization.dto.response.BaseResponseDTO;
import com.harry.JwtRoleBaseAuthorization.dto.response.UserResponseDTO;

import java.util.Map;
import java.util.Optional;

public interface IAuthService {
    Optional<BaseResponseDTO> login(LoginRequestDTO dto);

    Optional<UserResponseDTO> register(RegistrationRequestDTO dto);

    Optional<BaseResponseDTO> refreshToken(Map<String, Object> body);
}
