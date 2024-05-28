package com.budziak.springmenuapp.controller.web;

import com.budziak.springmenuapp.domain.UserEntity;
import com.budziak.springmenuapp.domain.UserRole;
import com.budziak.springmenuapp.dto.LoginDto;
import com.budziak.springmenuapp.dto.RegisterDto;
import com.budziak.springmenuapp.exeption.BadRequestException;
import com.budziak.springmenuapp.exeption.InternalServerErrorException;
import com.budziak.springmenuapp.repository.RoleRepository;
import com.budziak.springmenuapp.repository.UserRepository;
import com.budziak.springmenuapp.security.JwtGenerator;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtGenerator jwtGenerator, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userDto", new LoginDto());
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto, HttpServletResponse response) {
        authenticateAndSetCookie(loginDto.getUsername(), loginDto.getPassword(), response);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userDto", new RegisterDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterDto registerDto, HttpServletResponse response) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new BadRequestException("Username is taken!");
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        UserRole roles = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new InternalServerErrorException("An unexpected error"));

        user.setUserRoles(Collections.singleton(roles));
        userRepository.save(user);

        authenticateAndSetCookie(registerDto.getUsername(), registerDto.getPassword(), response);
        return "redirect:/";
    }

    private void authenticateAndSetCookie(String username, String password, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);

        Cookie cookie = new Cookie("Authorization", token);
        cookie.setPath("/");
        cookie.setMaxAge(86400);
        response.addCookie(cookie);
    }
}
