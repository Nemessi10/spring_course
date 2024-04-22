package com.budziak.springmenuapp.repository;

import com.budziak.springmenuapp.domain.FavouriteMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteMenuRepository extends JpaRepository<FavouriteMenu, Long> {
}
