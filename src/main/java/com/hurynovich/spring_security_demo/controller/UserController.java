package com.hurynovich.spring_security_demo.controller;

import com.hurynovich.spring_security_demo.dto.impl.UserDTO;
import com.hurynovich.spring_security_demo.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public String signUp(final @ModelAttribute("user") UserDTO userDTO) {
        userService.create(userDTO);
        return "redirect:/profile-in-page";
    }

    @PostMapping("/sign-in")
    public String signIn() {
        return "redirect:/profile-page";
    }

    @PostMapping("/sign-out")
    public String signOut() {
        return "main-page";
    }
}
