package com.budziak.springmenuapp.repository;

import com.budziak.springmenuapp.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByName(String name);
}
