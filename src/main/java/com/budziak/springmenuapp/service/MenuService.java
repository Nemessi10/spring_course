package com.budziak.springmenuapp.service;

import com.budziak.springmenuapp.domain.Dish;
import com.budziak.springmenuapp.domain.Menu;
import com.budziak.springmenuapp.domain.UserEntity;
import com.budziak.springmenuapp.dto.GenerateMenuDto;
import com.budziak.springmenuapp.dto.MenuDto;
import com.budziak.springmenuapp.exeption.DishNotFoundException;
import com.budziak.springmenuapp.exeption.MenuNotFoundException;
import com.budziak.springmenuapp.repository.DishRepository;
import com.budziak.springmenuapp.repository.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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

    public List<Menu> generateMenus(GenerateMenuDto userId, LocalDate startDate, LocalDate endDate) {
        List<Menu> menus = new ArrayList<>();
        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            Menu menu = generateMenusForDateRange(userId, date);
            menus.add(menu);
        }
        return menus;
    }


    private Menu generateMenusForDateRange(GenerateMenuDto menuDto, LocalDate date) {

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
                .date(date)
                .build();
        return menuRepository.save(menu);
    }

    public void replaceDishInMenu(Long menuId, Long dishId) throws MenuNotFoundException, DishNotFoundException {

        Menu menu = menuRepository.findById(menuId).orElseThrow(MenuNotFoundException::new);
        Dish oldDish = dishRepository.findById(dishId).orElseThrow(DishNotFoundException::new);

        List<Dish> replacementDishes = dishRepository.findByCategory(oldDish.getCategory());
        Collections.shuffle(replacementDishes);
        Dish newDish = replacementDishes.get(0);

        switch (oldDish.getCategory()) {
            case "Breakfast" -> menu.setBreakfast(newDish);
            case "Lunch" -> menu.setLunch(newDish);
            case "Dinner" -> menu.setDinner(newDish);
        }

        menuRepository.save(menu);
    }



    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }

    public void removeDishFromMenu(Long menuId, Long dishId) throws MenuNotFoundException, DishNotFoundException {
        Menu menu = menuRepository.findById(menuId).orElseThrow(MenuNotFoundException::new);

        if (menu.getBreakfast() != null && menu.getBreakfast().getId().equals(dishId)) {
            menu.setBreakfast(null);
        } else if (menu.getLunch() != null && menu.getLunch().getId().equals(dishId)) {
            menu.setLunch(null);
        } else if (menu.getDinner() != null && menu.getDinner().getId().equals(dishId)) {
            menu.setDinner(null);
        } else {
            throw new DishNotFoundException();
        }

        menuRepository.save(menu);
    }

}
