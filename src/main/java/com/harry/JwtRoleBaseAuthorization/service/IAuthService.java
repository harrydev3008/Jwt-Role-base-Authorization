package com.harry.JwtRoleBaseAuthorization.service;

import java.util.Optional;

import com.harry.JwtRoleBaseAuthorization.dto.request.LoginRequestDTO;
import com.harry.JwtRoleBaseAuthorization.dto.response.BaseResponseDTO;

public interface IAuthService {
    Optional<BaseResponseDTO> login(LoginRequestDTO dto);
}
