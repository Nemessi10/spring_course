package com.budziak.springmenuapp.controller.api;

import com.budziak.springmenuapp.domain.UserEntity;
import com.budziak.springmenuapp.exeption.NoContentException;
import com.budziak.springmenuapp.exeption.ResourceNotFoundException;
import com.budziak.springmenuapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserEntity> getAllUsers() {

        List<UserEntity> userEntities = userService.findAll();

        if (userEntities == null)
            throw new ResourceNotFoundException();
        else if (userEntities.isEmpty())
            throw new NoContentException();
        else return userEntities;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserEntity getUserById(@PathVariable Long id) {

        return userService.getUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User by " + id + " id not found"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
    }
}
