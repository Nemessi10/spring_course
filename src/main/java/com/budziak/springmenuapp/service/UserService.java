package com.budziak.springmenuapp.service;

import com.budziak.springmenuapp.domain.UserEntity;
import com.budziak.springmenuapp.domain.UserRole;
import com.budziak.springmenuapp.dto.UserDto;
import com.budziak.springmenuapp.repository.RoleRepository;
import com.budziak.springmenuapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final String defaultAdminUsername;

    private final String defaultAdminPassword;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(
            @Value("${admin.default.username}") String defaultAdminUsername,
            @Value("${admin.default.password}") String defaultAdminPassword,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            RoleRepository roleRepository) {
        this.defaultAdminUsername = defaultAdminUsername;
        this.defaultAdminPassword = defaultAdminPassword;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void createDefaultAdminIfNotExist() {

        boolean anyAdminExist = userRepository.isAnyAdminExist(roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new IllegalStateException("Role 'ROLE_ADMIN' not found. Cannot create default admin user")));

        if (anyAdminExist) {
            log.info("Admin already exists. Skipping creation of default admin user");
            return;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(defaultAdminUsername);
        userEntity.setPassword(passwordEncoder.encode(defaultAdminPassword));

        UserRole role = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new IllegalStateException("Role 'ROLE_ADMIN' not found. Cannot create default admin user"));

        userEntity.setUserRoles(Collections.singleton(role));

        userRepository.save(userEntity);

        log.info("Default admin user created successfully");
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
