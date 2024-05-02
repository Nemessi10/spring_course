package com.budziak.springmenuapp.controller.api;

import com.budziak.springmenuapp.domain.UserEntity;
import com.budziak.springmenuapp.dto.UserDto;
import com.budziak.springmenuapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> userEntities = userService.findAll();
        return new ResponseEntity<>(userEntities, HttpStatus.OK);
    }

    @GetMapping("/user")
    public UserEntity getUserById(@RequestParam Long id) {
        return userService.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("User by " + id + " id not found"));
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserDto user) {
        UserEntity createdUserEntity = userService.createUser(user);
        return new ResponseEntity<>(createdUserEntity, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
