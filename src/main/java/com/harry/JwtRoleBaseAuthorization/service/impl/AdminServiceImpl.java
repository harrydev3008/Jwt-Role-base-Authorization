package com.harry.JwtRoleBaseAuthorization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harry.JwtRoleBaseAuthorization.dto.response.UserResponseDTO;
import com.harry.JwtRoleBaseAuthorization.mapper.ModelMapperData;
import com.harry.JwtRoleBaseAuthorization.repository.IUserRepository;
import com.harry.JwtRoleBaseAuthorization.service.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Iterable<UserResponseDTO> loadAllUsers() {
        return ModelMapperData.mapMany(userRepository.findAll(), UserResponseDTO.class);
    }

}