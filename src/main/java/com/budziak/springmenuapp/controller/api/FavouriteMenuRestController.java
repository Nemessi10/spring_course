package com.budziak.springmenuapp.controller.api;

import com.budziak.springmenuapp.domain.FavouriteMenu;
import com.budziak.springmenuapp.dto.FavouriteMenuDto;
import com.budziak.springmenuapp.service.FavouriteMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/favourite-menus")
public class FavouriteMenuRestController {

    private final FavouriteMenuService favouriteMenuService;

    @Autowired
    public FavouriteMenuRestController(FavouriteMenuService favouriteMenuService) {
        this.favouriteMenuService = favouriteMenuService;
    }

    @PostMapping("/new/{userId}/{menuId}")
    public ResponseEntity<?> addMenuToFavourites(@PathVariable Long userId, @PathVariable Long menuId) {

        try {
            favouriteMenuService.addMenuToFavourites(userId, menuId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
