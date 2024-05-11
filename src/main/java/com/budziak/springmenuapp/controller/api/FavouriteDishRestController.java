package com.budziak.springmenuapp.controller.api;

import com.budziak.springmenuapp.service.FavouriteDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> addDishToFavourites(@PathVariable Long userId, @PathVariable Long dishId) {

        try {
            favouriteDishService.addDishToFavourites(userId, dishId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

