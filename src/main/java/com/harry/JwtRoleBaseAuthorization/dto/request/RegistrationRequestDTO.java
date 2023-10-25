package com.harry.JwtRoleBaseAuthorization.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegistrationRequestDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String repeatPassword;
}
