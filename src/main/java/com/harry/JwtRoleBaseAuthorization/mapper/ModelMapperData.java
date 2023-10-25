package com.harry.JwtRoleBaseAuthorization.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperData {
    private static ModelMapper modelMapper;

    public ModelMapperData(ModelMapper modelMapper) {
        ModelMapperData.modelMapper = modelMapper;
    }

    public static <T, C> C mapOne(T data, Class<C> outClass) {
        return modelMapper.map(data, outClass);
    }

    public static <T, C> List<C> mapMany(List<T> data, Class<C> outClass) {
        return data.stream().map(obj -> modelMapper.map(obj, outClass)).toList();
    }
}
