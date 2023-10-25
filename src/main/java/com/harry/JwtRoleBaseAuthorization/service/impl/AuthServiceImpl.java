package com.harry.JwtRoleBaseAuthorization.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.harry.JwtRoleBaseAuthorization.dto.request.LoginRequestDTO;
import com.harry.JwtRoleBaseAuthorization.dto.response.BaseResponseDTO;
import com.harry.JwtRoleBaseAuthorization.dto.response.UserResponseDTO;
import com.harry.JwtRoleBaseAuthorization.mapper.ModelMapperData;
import com.harry.JwtRoleBaseAuthorization.model.ApplicationUser;
import com.harry.JwtRoleBaseAuthorization.repository.IUserRepository;
import com.harry.JwtRoleBaseAuthorization.service.IAuthService;
import com.harry.JwtRoleBaseAuthorization.utils.JwtGenerator;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Optional<BaseResponseDTO> login(LoginRequestDTO dto) {

        ApplicationUser user = userRepository.findByUsername(dto.getUsername()).get();

        if (user == null)
            throw new UsernameNotFoundException("username not found!");

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                dto.getUsername(), dto.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if (authentication.isAuthenticated()) {
            UserResponseDTO responseDTO = ModelMapperData.mapOne(user, UserResponseDTO.class);
            JwtGenerator generator = new JwtGenerator(responseDTO);
            String accessToken = generator.generateToken(JwtGenerator.TokenType.ACCESS_TOKEN);
            String refreshToken = generator.generateToken(JwtGenerator.TokenType.REFRESH_TOKEN);

            return Optional.of(new BaseResponseDTO(responseDTO, accessToken, refreshToken));
        }

        return Optional.empty();
    }

}
