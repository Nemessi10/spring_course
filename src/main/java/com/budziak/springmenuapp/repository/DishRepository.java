package com.budziak.springmenuapp.repository;

import com.budziak.springmenuapp.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    Dish findByName(String name);

    List<Dish> findByCategory(String category);

    List<Dish> findAllByCategoryIn(List<String> categories);
}
