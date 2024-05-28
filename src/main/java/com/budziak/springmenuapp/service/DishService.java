package com.budziak.springmenuapp.service;

import com.budziak.springmenuapp.domain.Dish;
import com.budziak.springmenuapp.domain.Image;
import com.budziak.springmenuapp.dto.DishDto;
import com.budziak.springmenuapp.exeption.ResourceNotFoundException;
import com.budziak.springmenuapp.repository.DishRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DishService {

    private final DishRepository dishRepository;
    private final ImageService imageService;

    @Autowired
    public DishService(DishRepository dishRepository, ImageService imageService) {
        this.dishRepository = dishRepository;
        this.imageService = imageService;
    }

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Optional<Dish> getDishById(Long id) {
        return dishRepository.findById(id);
    }

    public Dish createDish(DishDto dishDto, MultipartFile imageFile) throws IOException {

        Image image = imageService.uploadImageToFileSystem(imageFile);
        Dish dish = Dish.builder()
                .name(dishDto.getName())
                .image(image)
                .ingredients(dishDto.getIngredients())
                .recipe(dishDto.getRecipe())
                .category(dishDto.getCategory())
                .build();

        return dishRepository.save(dish);
    }

    public Dish updateDish(Long id, DishDto dishDto, MultipartFile imageFile) throws IOException {

        Dish existingDish = dishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found with id " + id));

        Dish updatedDish = Dish.builder()
                .id(existingDish.getId())
                .name(Optional.ofNullable(dishDto.getName()).orElse(existingDish.getName()))
                .image(updateImage(imageFile, existingDish.getImage()))
                .ingredients(Optional.ofNullable(dishDto.getIngredients()).orElse(existingDish.getIngredients()))
                .recipe(Optional.ofNullable(dishDto.getRecipe()).orElse(existingDish.getRecipe()))
                .category(Optional.ofNullable(dishDto.getCategory()).orElse(existingDish.getCategory()))
                .build();

        return dishRepository.save(updatedDish);
    }

    private Image updateImage(MultipartFile imageFile, Image existingImage) throws IOException {

        if (imageFile != null && !imageFile.isEmpty()) {
            return imageService.uploadImageToFileSystem(imageFile);
        } else {
            return existingImage;
        }
    }

    public byte[] getDishImage(Long dishId) throws IOException {

        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found with id " + dishId));

        if (dish.getImage() == null) {
            throw new ResourceNotFoundException("Dish doesn't have an image associated");
        }

        return imageService.downloadImageFromFileSystem(dish.getImage().getFilePath());
    }


    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }
}
