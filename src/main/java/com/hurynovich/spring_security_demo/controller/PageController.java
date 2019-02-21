package com.hurynovich.spring_security_demo.controller;

import com.hurynovich.spring_security_demo.dto.impl.UserDTO;
import com.hurynovich.spring_security_demo.security.UserDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PageController {
    @GetMapping(path = {"/", "/main-page"})
    public String mainPage() {
        return "main-page";
    }

    @GetMapping("/user-page")
    public String userPage() {
        return "user-page";
    }

    @GetMapping("/admin-page")
    public String adminPage() {
        return "admin-page";
    }

    @GetMapping("/profile-page")
    public String profilePage(final Principal principal, final Model model) {
        if (principal != null) {
            final UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
            final UserDetailsImpl userDetails = (UserDetailsImpl) authenticationToken.getPrincipal();
            final UserDTO authenticatedUser = new UserDTO();
            authenticatedUser.setName(userDetails.getName());
            authenticatedUser.setEmail(userDetails.getUsername());
            authenticatedUser.setRoles(userDetails.getRoles());
            model.addAttribute("user", authenticatedUser);
            return "profile-page";
        } else {
            return "sign-in-page";
        }
    }

    @GetMapping("/sign-up-page")
    public String signUpPage(final Model model) {
        model.addAttribute("user", new UserDTO());
        return "sign-up-page";
    }

    @GetMapping("/sign-in-page")
    public String signInPage(final Model model) {
        model.addAttribute("user", new UserDTO());
        return "sign-in-page";
    }
}
