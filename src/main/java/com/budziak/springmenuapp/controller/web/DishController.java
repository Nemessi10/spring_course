package com.budziak.springmenuapp.controller.web;

import com.budziak.springmenuapp.domain.Dish;
import com.budziak.springmenuapp.domain.Menu;
import com.budziak.springmenuapp.dto.DishDto;
import com.budziak.springmenuapp.exeption.NoContentException;
import com.budziak.springmenuapp.exeption.ResourceNotFoundException;
import com.budziak.springmenuapp.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/dishes")
public class DishController {
    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public String getAllDishes(Model model) {
        List<Dish> dishes = dishService.getAllDishes();
        model.addAttribute("dishes", dishes);
        return "dishes";
    }
}
