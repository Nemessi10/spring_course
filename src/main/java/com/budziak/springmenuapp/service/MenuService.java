package com.budziak.springmenuapp.service;

import com.budziak.springmenuapp.domain.Dish;
import com.budziak.springmenuapp.domain.Menu;
import com.budziak.springmenuapp.domain.UserEntity;
import com.budziak.springmenuapp.dto.GenerateMenuDto;
import com.budziak.springmenuapp.dto.MenuDto;
import com.budziak.springmenuapp.repository.DishRepository;
import com.budziak.springmenuapp.repository.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final DishRepository dishRepository;
    private final UserService userService;

    @Autowired
    public MenuService(MenuRepository menuRepository, DishRepository dishRepository, UserService userService) {
        this.menuRepository = menuRepository;
        this.dishRepository = dishRepository;
        this.userService = userService;
    }

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Optional<Menu> getMenuById(Long id) {
        return menuRepository.findById(id);
    }

    public List<Menu> getMenusByUser(UserEntity userEntity) {
        return menuRepository.findByUserEntity(userEntity);
    }

    public List<Menu> getMenusByDate(Date date) {
        return menuRepository.findByDate(date);
    }

    /*public Menu generateMenu(GenerateMenuDto menuDto, Long userId) {
        UserEntity userEntity = userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Dish> breakfastDishes = dishRepository.findByCategory("Breakfast");
        List<Dish> lunchDishes = dishRepository.findByCategory("Lunch");
        List<Dish> dinnerDishes = dishRepository.findByCategory("Dinner");

        if (breakfastDishes.isEmpty() || lunchDishes.isEmpty() || dinnerDishes.isEmpty()) {
            throw new IllegalStateException("Not enough dishes in each category to generate a menu");
        }

        Dish breakfast = breakfastDishes.get((int) (Math.random() * breakfastDishes.size()));
        Dish lunch = lunchDishes.get((int) (Math.random() * lunchDishes.size()));
        Dish dinner = dinnerDishes.get((int) (Math.random() * dinnerDishes.size()));

        Menu menu = Menu.builder()
                .userEntity(userEntity)
                .breakfast(breakfast)
                .lunch(lunch)
                .dinner(dinner)
                .date(menuDto.getDate())
                .build();

        return menuRepository.save(menu);
    }*/

    public Menu generateMenu(GenerateMenuDto menuDto) {

        UserEntity userId = userService.getUserById(menuDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<String> categories = Arrays.asList("Breakfast", "Lunch", "Dinner");
        List<Dish> allDishes = dishRepository.findAllByCategoryIn(categories);

        if (allDishes.isEmpty() || allDishes.stream()
                .noneMatch(dish -> dish.getCategory().equals("Breakfast"))
                || allDishes.stream()
                .noneMatch(dish -> dish.getCategory().equals("Lunch"))
                || allDishes.stream()
                .noneMatch(dish -> dish.getCategory().equals("Dinner"))) {
            throw new IllegalStateException("Not enough dishes in each category to generate a menu");
        }


        Collections.shuffle(allDishes);

        Dish breakfast = allDishes.stream()
                .filter(dish -> dish.getCategory().equals("Breakfast"))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No breakfast dish found"));

        Dish lunch = allDishes.stream()
                .filter(dish -> dish.getCategory().equals("Lunch"))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No lunch dish found"));

        Dish dinner = allDishes.stream()
                .filter(dish -> dish.getCategory().equals("Dinner"))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No dinner dish found"));

        Menu menu = Menu.builder()
                .userEntity(userId)
                .breakfast(breakfast)
                .lunch(lunch)
                .dinner(dinner)
                .date(menuDto.getDate())
                .build();
        return menuRepository.save(menu);
    }


    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }
}
