package com.hurynovich.spring_security_demo.dto.impl;

import com.hurynovich.spring_security_demo.dto.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDTO extends AbstractDTO {
    private String name;
    private String email;
    private String password;
    private List<UserRoleDTO> roles;
}
