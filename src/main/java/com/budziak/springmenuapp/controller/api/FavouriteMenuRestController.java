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
@RequestMapping("api/favourites")
public class FavouriteMenuRestController {

    private final FavouriteMenuService favouriteMenuService;

    @Autowired
    public FavouriteMenuRestController(FavouriteMenuService favouriteMenuService) {
        this.favouriteMenuService = favouriteMenuService;
    }

    @GetMapping
    public ResponseEntity<List<FavouriteMenu>> getAllFavourites() {
        List<FavouriteMenu> favouriteMenus = favouriteMenuService.getAllFavourite();
        return new ResponseEntity<>(favouriteMenus, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FavouriteMenu> createFavourite(@RequestBody FavouriteMenuDto favouriteMenuDto, Long userId) {
        FavouriteMenu createdFavouriteMenu = favouriteMenuService.createFavourite(favouriteMenuDto, userId);
        return new ResponseEntity<>(createdFavouriteMenu, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<FavouriteMenu> deleteFavourite(@PathVariable Long id) {
        favouriteMenuService.deleteFavourite(id);
        return ResponseEntity.noContent().build();
    }
}
