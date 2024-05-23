package com.budziak.springmenuapp.controller.api;

import com.budziak.springmenuapp.exeption.*;
import com.budziak.springmenuapp.service.FavouriteDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favourite-dishes")
public class FavouriteDishRestController {

    private final FavouriteDishService favouriteDishService;

    @Autowired
    public FavouriteDishRestController(FavouriteDishService favouriteDishService) {
        this.favouriteDishService = favouriteDishService;
    }

    @PostMapping("/new/{userId}/{dishId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addDishToFavourites(@PathVariable Long userId, @PathVariable Long dishId) {

        try {
            favouriteDishService.addDishToFavourites(userId, dishId);
        } catch (DishNotFoundException e) {
            throw new ResourceNotFoundException("Dish not found with id " + dishId);
        } catch (DishAlreadyInFavoritesException e) {
            throw new BadRequestException("Dish already exists in favourites for user " + userId);
        } catch (Exception e) {
            throw new InternalServerErrorException("An unexpected error occurred while adding dish to favourites");
        }
    }
}

