package com.budziak.springmenuapp.repository;

import com.budziak.springmenuapp.domain.FavouriteDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteDishRepository extends JpaRepository<FavouriteDish, Long>  {

    FavouriteDish findByUserEntityIdAndDishId(Long userId, Long dishId);
}
