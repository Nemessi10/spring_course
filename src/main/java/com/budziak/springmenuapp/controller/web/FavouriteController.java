package com.budziak.springmenuapp.controller.web;

import com.budziak.springmenuapp.domain.FavouriteMenu;
import com.budziak.springmenuapp.dto.FavouriteMenuDto;
import com.budziak.springmenuapp.service.FavouriteMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("favourites")
public class FavouriteController {

    private final FavouriteMenuService favouriteMenuService;

    @Autowired
    public FavouriteController(FavouriteMenuService favouriteMenuService) {
        this.favouriteMenuService = favouriteMenuService;
    }

    @GetMapping
    public String getAllFavourite(Model model) {
        List<FavouriteMenu> favouriteMenus = favouriteMenuService.getAllFavourite();
        model.addAttribute("favourites", favouriteMenus);
        return "favourite/index";
    }

    @PostMapping("/new")
    public String createFavourite(FavouriteMenuDto favouriteMenuDto, Long userId) {
        favouriteMenuService.createFavourite(favouriteMenuDto, userId);
        return "redirect:/favourites";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavourite(@PathVariable Long id) {
        favouriteMenuService.deleteFavourite(id);
        return ResponseEntity.noContent().build();
    }
}
