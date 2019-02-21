package com.hurynovich.spring_security_demo.security;

import com.hurynovich.spring_security_demo.dto.impl.UserDTO;
import com.hurynovich.spring_security_demo.dto.impl.UserRoleDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsImpl implements UserDetails {
    private UserDTO userDTO;

    public void setUserDTO(final UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDTO.getRoles().stream().map(this::convertUserRoleToGrantedAuthority).collect(Collectors.toList());
    }

    private GrantedAuthority convertUserRoleToGrantedAuthority(final UserRoleDTO userRoleDTO) {
        return userRoleDTO::getName;
    }

    public String getName() {
        return userDTO.getName();
    }

    public List<UserRoleDTO> getRoles() {
        return userDTO.getRoles();
    }

    @Override
    public String getUsername() {
        return userDTO.getEmail();
    }

    @Override
    public String getPassword() {
        return userDTO.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
