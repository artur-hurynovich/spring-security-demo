package com.hurynovich.spring_security_demo.converter.impl;

import com.hurynovich.spring_security_demo.converter.DTOEntityConverter;
import com.hurynovich.spring_security_demo.dto.impl.UserDTO;
import com.hurynovich.spring_security_demo.dto.impl.UserRoleDTO;
import com.hurynovich.spring_security_demo.entity.impl.UserEntity;
import com.hurynovich.spring_security_demo.entity.impl.UserRoleEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDTOEntityConverter implements DTOEntityConverter<UserDTO, UserEntity> {
    private final DTOEntityConverter<UserRoleDTO, UserRoleEntity> roleConverter;

    @Autowired
    public UserDTOEntityConverter(final DTOEntityConverter<UserRoleDTO, UserRoleEntity> roleConverter) {
        this.roleConverter = roleConverter;
    }

    @Override
    public UserDTO convert(final UserEntity userEntity) {
        final UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userEntity, userDTO, "roles");
        userDTO.setRoles(userEntity.getRoles().stream().map(roleConverter::convert).collect(Collectors.toList()));
        return userDTO;
    }

    @Override
    public UserEntity convert(final UserDTO userDTO) {
        final UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity, "roles");
        userEntity.setRoles(userDTO.getRoles().stream().map(roleConverter::convert).collect(Collectors.toList()));
        return userEntity;
    }
}
