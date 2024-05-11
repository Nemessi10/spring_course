package com.budziak.springmenuapp.repository;

import com.budziak.springmenuapp.domain.Menu;
import com.budziak.springmenuapp.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByUserEntity(UserEntity userEntity);
    List<Menu> findByDate(Date date);
}
