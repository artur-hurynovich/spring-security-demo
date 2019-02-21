package com.hurynovich.spring_security_demo.dto.impl;

import com.hurynovich.spring_security_demo.dto.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserRoleDTO extends AbstractDTO {
    private String name;
    private List<UserDTO> users;
}
