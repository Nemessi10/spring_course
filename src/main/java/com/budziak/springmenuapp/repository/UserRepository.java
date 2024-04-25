package com.budziak.springmenuapp.repository;

import com.budziak.springmenuapp.domain.UserEntity;
import com.budziak.springmenuapp.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>/* implements JpaSpecificationExecutor*/ {
    List<UserEntity> findAll();
    UserEntity findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
    void deleteByUsername(String username);
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserEntity u WHERE :adminRole MEMBER OF u.userRoles")
    boolean isAnyAdminExist(UserRole adminRole);
}
