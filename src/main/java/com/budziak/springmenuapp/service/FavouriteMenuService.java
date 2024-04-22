package com.budziak.springmenuapp.service;

import com.budziak.springmenuapp.domain.FavouriteMenu;
import com.budziak.springmenuapp.domain.UserEntity;
import com.budziak.springmenuapp.dto.FavouriteMenuDto;
import com.budziak.springmenuapp.repository.FavouriteMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteMenuService {

    private final FavouriteMenuRepository favouriteMenuRepository;
    private final UserService userService;

    @Autowired
    public FavouriteMenuService(FavouriteMenuRepository favouriteMenuRepository, UserService userService) {
        this.favouriteMenuRepository = favouriteMenuRepository;
        this.userService = userService;
    }

    public FavouriteMenu createFavourite(FavouriteMenuDto favouriteMenuDto, Long userId) {
        UserEntity userEntity = userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        FavouriteMenu favouriteMenu = FavouriteMenu.builder()
                .userEntity(userEntity)
                .menu(favouriteMenuDto.getMenu())
                .build();

        return favouriteMenuRepository.save(favouriteMenu);
    }

    public void deleteFavourite(Long id) {
        favouriteMenuRepository.deleteById(id);
    }

    public List<FavouriteMenu> getAllFavourite() {
        return favouriteMenuRepository.findAll();
    }
}
