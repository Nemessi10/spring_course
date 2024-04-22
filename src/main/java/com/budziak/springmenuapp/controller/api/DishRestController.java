package com.budziak.springmenuapp.controller.api;

import com.budziak.springmenuapp.domain.Dish;
import com.budziak.springmenuapp.dto.DishDto;
import com.budziak.springmenuapp.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
public class DishRestController {
    private final DishService dishService;

    @Autowired
    public DishRestController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public ResponseEntity<List<Dish>> getAllMenus() {
        List<Dish> dishes = dishService.getAllDishes();
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getMenuById(@PathVariable Long id) {
        dishService.getDishById(id);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Dish> createMenu(@RequestBody DishDto dish) {
        Dish createdDish = dishService.createDish(dish);
        return new ResponseEntity<>(createdDish, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Dish> deleteMenu(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
}
