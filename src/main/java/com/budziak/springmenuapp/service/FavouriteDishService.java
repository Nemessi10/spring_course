package com.budziak.springmenuapp.service;

import com.budziak.springmenuapp.domain.FavouriteDish;
import com.budziak.springmenuapp.domain.UserEntity;
import com.budziak.springmenuapp.dto.FavouriteDishDto;
import com.budziak.springmenuapp.repository.FavouriteDishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteDishService {

    private final FavouriteDishRepository favouriteDishRepository;
    private final UserService userService;

    @Autowired
    public FavouriteDishService(FavouriteDishRepository favouriteDishRepository, UserService userService) {
        this.favouriteDishRepository = favouriteDishRepository;
        this.userService = userService;
    }

    public FavouriteDish createFavourite(FavouriteDishDto favouriteDishDto, Long userId) {
        UserEntity userEntity = userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        FavouriteDish favouriteDish = FavouriteDish.builder()
                .userEntity(userEntity)
                .dish(favouriteDishDto.getDish())
                .build();

        return favouriteDishRepository.save(favouriteDish);
    }

    public void deleteFavourite(Long id) {
        favouriteDishRepository.deleteById(id);
    }

    public List<FavouriteDish> getAllFavourite() {
        return favouriteDishRepository.findAll();
    }
}
