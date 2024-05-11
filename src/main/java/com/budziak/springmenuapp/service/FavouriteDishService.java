package com.budziak.springmenuapp.service;

import com.budziak.springmenuapp.domain.Dish;
import com.budziak.springmenuapp.domain.FavouriteDish;
import com.budziak.springmenuapp.domain.UserEntity;
import com.budziak.springmenuapp.exeption.DishAlreadyInFavoritesException;
import com.budziak.springmenuapp.repository.FavouriteDishRepository;
import org.springframework.stereotype.Service;

@Service
public class FavouriteDishService {

    private final FavouriteDishRepository favouriteDishRepository;

    public FavouriteDishService(FavouriteDishRepository favouriteDishRepository) {
        this.favouriteDishRepository = favouriteDishRepository;
    }

    public void addDishToFavourites(Long userId, Long dishId) {
        FavouriteDish favouriteDish = favouriteDishRepository.findByUserEntityIdAndDishId(userId, dishId);
        if (favouriteDish == null) {
            favouriteDishRepository.save(FavouriteDish.builder()
                    .userEntity(UserEntity.builder().id(userId).build())
                    .dish(Dish.builder().id(dishId).build())
                    .build());
        } else {
            throw new DishAlreadyInFavoritesException("This dish is already in favourites");
        }
    }
}

