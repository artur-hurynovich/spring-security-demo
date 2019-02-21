package com.hurynovich.spring_security_demo.converter.impl;

import com.hurynovich.spring_security_demo.converter.DTOEntityConverter;
import com.hurynovich.spring_security_demo.dto.impl.UserRoleDTO;
import com.hurynovich.spring_security_demo.entity.impl.UserRoleEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserRoleDTOEntityConverter implements DTOEntityConverter<UserRoleDTO, UserRoleEntity> {
    @Override
    public UserRoleDTO convert(final UserRoleEntity userRoleEntity) {
        final UserRoleDTO userRoleDTO = new UserRoleDTO();
        BeanUtils.copyProperties(userRoleEntity, userRoleDTO, "users");
        return userRoleDTO;
    }

    @Override
    public UserRoleEntity convert(final UserRoleDTO userRoleDTO) {
        final UserRoleEntity userRoleEntity = new UserRoleEntity();
        BeanUtils.copyProperties(userRoleDTO, userRoleEntity, "users");
        return userRoleEntity;
    }
}
