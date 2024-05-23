package com.budziak.springmenuapp.service;

import com.budziak.springmenuapp.domain.FavouriteMenu;
import com.budziak.springmenuapp.domain.Menu;
import com.budziak.springmenuapp.domain.UserEntity;
import com.budziak.springmenuapp.dto.FavouriteMenuDto;
import com.budziak.springmenuapp.exeption.MenuAlreadyInFavoritesException;
import com.budziak.springmenuapp.repository.FavouriteMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteMenuService {

    private final FavouriteMenuRepository favouriteMenuRepository;

    @Autowired
    public FavouriteMenuService(FavouriteMenuRepository favouriteMenuRepository) {
        this.favouriteMenuRepository = favouriteMenuRepository;
    }

    public void addMenuToFavourites(Long userId, Long menuId) {

        FavouriteMenu favouriteMenu = favouriteMenuRepository.findByUserEntityIdAndMenuId(userId, menuId);
        if(favouriteMenu == null) {
            favouriteMenuRepository.save(FavouriteMenu.builder()
                    .userEntity(UserEntity.builder().id(userId).build())
                    .menu(Menu.builder().id(menuId).build())
                    .build());
        } else {
            throw new MenuAlreadyInFavoritesException("This menu is already in favourites");
        }
    }
}
