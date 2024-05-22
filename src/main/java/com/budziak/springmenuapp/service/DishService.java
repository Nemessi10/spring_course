package com.budziak.springmenuapp.service;

import com.budziak.springmenuapp.domain.Dish;
import com.budziak.springmenuapp.domain.Image;
import com.budziak.springmenuapp.dto.DishDto;
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

    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }

    public Dish findByName(String name) {
        return dishRepository.findByName(name);
    }

    public void save(Dish dish) {
        dishRepository.save(dish);
    }
}
