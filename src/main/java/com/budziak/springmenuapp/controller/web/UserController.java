package com.budziak.springmenuapp.controller.web;

import com.budziak.springmenuapp.domain.UserEntity;
import com.budziak.springmenuapp.dto.UserDto;
import com.budziak.springmenuapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getAllUsers(Model model) {
        List<UserEntity> userEntities = userService.findAll();
        model.addAttribute("users", userEntities);
        return "user/index";
    }

    @GetMapping("/{username}")
    public String getUserByUsername(@PathVariable String username, Model model) {
        UserEntity userEntity = userService.getUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        model.addAttribute("user", userEntity);
        return "user/userDetails";
    }


    @PostMapping("/new")
    public String createUser(UserDto userDto) {
        userService.createUser(userDto);
        return "redirect:/users";
    }

    /*@PostMapping("/register")
    public ResponseEntity<Void> registerUser(UserDto userDto) {
        try {
            userService.registerUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }*/

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @DeleteMapping("/{username}")
    public String deleteByUsername(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return "redirect:/users";
    }

}
