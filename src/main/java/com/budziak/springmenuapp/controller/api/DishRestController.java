package com.budziak.springmenuapp.controller.api;

import com.budziak.springmenuapp.domain.Dish;
import com.budziak.springmenuapp.dto.DishDto;
import com.budziak.springmenuapp.service.DishService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.MultipartConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.authority.AuthorityUtils;


import java.io.IOException;
import java.util.List;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasAuthority;

@Slf4j
@RestController
@MultipartConfig
@RequestMapping("/api/dishes")
public class DishRestController {
    private final DishService dishService;

    @Autowired
    public DishRestController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public ResponseEntity<List<Dish>> getAllDishes() {

        List<Dish> dishes = dishService.getAllDishes();

        if (dishes == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if (dishes.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable Long id) {
        dishService.getDishById(id);
        return ResponseEntity.notFound().build();
    }

    /*@PostMapping
    public ResponseEntity<Dish> createDish(@RequestParam(value = "json") DishDto dishDto, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {

        if (!SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(AuthorityUtils.createAuthorityList("ADMIN").get(0))) {
            log.error("Користувач не має ролі ADMIN, доступ заборонено");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Dish dish = dishService.createDish(dishDto, imageFile);
        return new ResponseEntity<>(dish, HttpStatus.CREATED);
    }*/

    //@ResponseStatus
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Dish> createDish(@RequestParam(value = "json") String dishDtoJson, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        DishDto dishDto;

        try {
            dishDto = objectMapper.readValue(dishDtoJson, DishDto.class);
        } catch (Exception e) {
            log.error("Помилка конвертації JSON у об'єкт DishDto", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(AuthorityUtils.createAuthorityList("ADMIN").get(0))) {
            log.error("Користувач не має ролі ADMIN, доступ заборонено");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Dish dish = dishService.createDish(dishDto, imageFile);
        return new ResponseEntity<>(dish, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Dish> deleteMenu(@PathVariable Long id) {

        if (!SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(AuthorityUtils.createAuthorityList("ADMIN").get(0))) {
            log.error("Користувач не має ролі ADMIN, доступ заборонено");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
}
