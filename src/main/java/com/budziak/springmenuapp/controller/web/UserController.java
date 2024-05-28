package com.budziak.springmenuapp.controller.web;

import com.budziak.springmenuapp.domain.UserEntity;
import com.budziak.springmenuapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAllUsers(Model model) {

        List<UserEntity> userEntities = userService.findAll();
        model.addAttribute("users", userEntities);

        return "users";
    }

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable Long id, Model model) {

        UserEntity user = userService.getUserById(id).orElseThrow();
        model.addAttribute("user", user);

        return "user";
    }
}
