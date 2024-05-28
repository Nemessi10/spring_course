package com.budziak.springmenuapp.controller.api;

import com.budziak.springmenuapp.domain.Dish;
import com.budziak.springmenuapp.dto.DishDto;
import com.budziak.springmenuapp.exeption.NoContentException;
import com.budziak.springmenuapp.exeption.ResourceNotFoundException;
import com.budziak.springmenuapp.service.DishService;
import com.budziak.springmenuapp.service.ImageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.MultipartConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@MultipartConfig
@RequestMapping("/api/dishes")
public class DishRestController {

    private final DishService dishService;
    private final ImageService imageService;

    @Autowired
    public DishRestController(DishService dishService, ImageService imageService) {
        this.dishService = dishService;
        this.imageService = imageService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Dish> getAllDishes() {

        List<Dish> dishes = dishService.getAllDishes();

        if (dishes == null)
            throw new ResourceNotFoundException();
        else if (dishes.isEmpty())
            throw new NoContentException();
        else return dishes;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Dish getDishById(@PathVariable Long id) {

        return dishService.getDishById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dish with " + id + " id not found"));
    }

    @GetMapping("/img/{fileName}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<byte[]> getImageByDishId(@PathVariable String fileName) throws IOException {

        byte[] imageBytes = imageService.downloadImageFromFileSystem(fileName);
        if (imageBytes == null) {
            throw new ResourceNotFoundException("Image not found with name: " + fileName);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Dish createDish(@RequestParam(value = "json") String dishDtoJson, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        DishDto dishDto;

        try {
            dishDto = objectMapper.readValue(dishDtoJson, DishDto.class);
        } catch (Exception e) {
            throw new BadRequestException("Error converting JSON into DishDto" + e.getMessage());
        }

        return dishService.createDish(dishDto, imageFile);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Dish updateDish(
            @PathVariable Long id,
            @RequestParam(value = "json") String dishDtoJson,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        DishDto dishDto;

        try {
            dishDto = objectMapper.readValue(dishDtoJson, DishDto.class);
        } catch (Exception e) {
            throw new BadRequestException("Error converting JSON into DishDto" + e.getMessage());
        }

        return dishService.updateDish(id, dishDto, imageFile);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteDish(@PathVariable Long id) {

        dishService.deleteDish(id);
    }
}
