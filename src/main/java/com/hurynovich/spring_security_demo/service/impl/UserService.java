package com.hurynovich.spring_security_demo.service.impl;

import com.hurynovich.spring_security_demo.converter.DTOEntityConverter;
import com.hurynovich.spring_security_demo.dto.impl.UserDTO;
import com.hurynovich.spring_security_demo.dto.impl.UserRoleDTO;
import com.hurynovich.spring_security_demo.entity.impl.UserEntity;
import com.hurynovich.spring_security_demo.entity.impl.UserRoleEntity;
import com.hurynovich.spring_security_demo.repository.UserRepository;
import com.hurynovich.spring_security_demo.repository.UserRoleRepository;
import com.hurynovich.spring_security_demo.security.UserDetailsImpl;
import com.hurynovich.spring_security_demo.service.DTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService, DTOService<UserDTO, Long> {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsImpl userDetails;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final DTOEntityConverter<UserDTO, UserEntity> userConverter;
    private final DTOEntityConverter<UserRoleDTO, UserRoleEntity> userRoleConverter;

    @Autowired
    public UserService(final PasswordEncoder passwordEncoder, final UserDetailsImpl userDetails,
                       final UserRepository userRepository,
                       final UserRoleRepository userRoleRepository,
                       final DTOEntityConverter<UserDTO, UserEntity> userConverter,
                       final DTOEntityConverter<UserRoleDTO, UserRoleEntity> userRoleConverter) {
        this.passwordEncoder = passwordEncoder;
        this.userDetails = userDetails;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userConverter = userConverter;
        this.userRoleConverter = userRoleConverter;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) {
        final UserDTO userDTO = userConverter.convert(userRepository.findByEmail(email));
        if (userDTO == null) {
            throw new UsernameNotFoundException("User with e-mail '" + email + "' not found!");
        } else {
            userDetails.setUserDTO(userDTO);
            return userDetails;
        }
    }

    @Override
    public UserDTO create(final UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        final List<UserRoleDTO> roles = new ArrayList<>();
        roles.add(userRoleConverter.convert(userRoleRepository.findByName("USER")));
        userDTO.setRoles(roles);
        return userConverter.convert(userRepository.save(userConverter.convert(userDTO)));
    }

    @Override
    public UserDTO readById(final Long id) {
        return userConverter.convert(userRepository.findById(id).orElse(null));
    }

    @Override
    public List<UserDTO> readAll() {
        return userRepository.findAll().stream().map(userConverter::convert).collect(Collectors.toList());
    }

    @Override
    public UserDTO update(final UserDTO userDTO) {
        return userConverter.convert(userRepository.save(userConverter.convert(userDTO)));
    }

    @Override
    public void delete(final Long id) {
        userRepository.deleteById(id);
    }
}
