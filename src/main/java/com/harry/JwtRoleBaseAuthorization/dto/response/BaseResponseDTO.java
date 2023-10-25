package com.harry.JwtRoleBaseAuthorization.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BaseResponseDTO {
    private UserResponseDTO data;
    private String accessToken;
    private String refreshToken;
}
