package com.budziak.springmenuapp.service;

import com.budziak.springmenuapp.domain.Dish;
import com.budziak.springmenuapp.dto.DishDto;
import com.budziak.springmenuapp.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    private final DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Optional<Dish> getDishById(Long id) {
        return dishRepository.findById(id);
    }

    public Dish createDish(DishDto dishDto) {

        Dish dish = Dish.builder()
                .name(dishDto.getName())
                .image(dishDto.getImage())
                .ingredients(dishDto.getIngredients())
                .recipe(dishDto.getRecipe())
                .category(dishDto.getCategory())
                .build();

        return dishRepository.save(dish);
    }

    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }
}
