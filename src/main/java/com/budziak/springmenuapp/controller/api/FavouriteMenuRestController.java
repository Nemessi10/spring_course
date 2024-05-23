package com.budziak.springmenuapp.controller.api;

import com.budziak.springmenuapp.exeption.*;
import com.budziak.springmenuapp.service.FavouriteMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/favourite-menus")
public class FavouriteMenuRestController {

    private final FavouriteMenuService favouriteMenuService;

    @Autowired
    public FavouriteMenuRestController(FavouriteMenuService favouriteMenuService) {
        this.favouriteMenuService = favouriteMenuService;
    }

    @PostMapping("/new/{userId}/{menuId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMenuToFavourites(@PathVariable Long userId, @PathVariable Long menuId) {

        try {
            favouriteMenuService.addMenuToFavourites(userId, menuId);
        } catch (MenuNotFoundException e) {
            throw new ResourceNotFoundException("Menu not found with id " + menuId);
        } catch (MenuAlreadyInFavoritesException e) {
            throw new BadRequestException("Menu already exists in favourites for user " + userId);
        } catch (Exception e) {
            throw new InternalServerErrorException("An unexpected error occurred while adding menu to favourites");
        }
    }
}
