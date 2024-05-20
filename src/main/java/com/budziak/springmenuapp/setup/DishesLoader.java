package com.budziak.springmenuapp.setup;

import com.budziak.springmenuapp.domain.Dish;
import com.budziak.springmenuapp.service.DishService;
import lombok.NonNull;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DishesLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;
    private final DishService dishService;

    public DishesLoader(DishService dishService) {
        this.dishService = dishService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(@NonNull final ContextRefreshedEvent event) {
        if (alreadySetup) return;

        // Create dishes
        createDishIfNotExists("Pancakes with Berries", "pancakes_with_berries.jpg",
                "Flour, eggs, milk, sugar, butter, berries",
                """
                        1. Mix flour, eggs, milk, and sugar to create a batter.
                        2. Melt butter in a pan and pour batter to make pancakes.
                        3. Serve pancakes with your favorite berries.""",
                "Breakfast");

        createDishIfNotExists("Chicken Caesar Salad", "caesar_salad.jpg",
                "Chicken breast, romaine lettuce, parmesan cheese, croutons, Caesar dressing",
                """
                        1. Grill or cook chicken breast.
                        2. Wash and chop romaine lettuce. Grate parmesan cheese.
                        3. Combine lettuce, chicken, croutons, and Caesar dressing. Top with parmesan cheese.""",
                "Lunch");

        createDishIfNotExists("Pasta with Meatballs", "pasta_with_meatballs.jpg",
                "Ground beef, breadcrumbs, egg, pasta, tomato sauce",
                """
                        1. Mix ground beef, breadcrumbs, and egg to create meatballs. Cook meatballs.
                        2. Cook pasta according to package instructions.
                        3. Combine cooked pasta, meatballs, and tomato sauce. Serve hot.""",
                "Dinner");
        
        alreadySetup = true;
    }

    @Transactional
    protected void createDishIfNotExists(String name, String image, String ingredients, String recipe, String category) {
        Dish dish = dishService.findByName(name);
        if (dish == null) {
            dish = new Dish();
            dish.setName(name);
            //dish.setImage(image);
            dish.setIngredients(ingredients);
            dish.setRecipe(recipe);
            dish.setCategory(category);

            dishService.save(dish);
        }
    }
}
