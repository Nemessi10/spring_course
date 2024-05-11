package com.budziak.springmenuapp.controller.api;

import com.budziak.springmenuapp.domain.Menu;
import com.budziak.springmenuapp.domain.UserEntity;
import com.budziak.springmenuapp.dto.GenerateMenuDto;
import com.budziak.springmenuapp.exeption.DishNotFoundException;
import com.budziak.springmenuapp.exeption.MenuNotFoundException;
import com.budziak.springmenuapp.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuRestController {

    private final MenuService menuService;

    @Autowired
    public MenuRestController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity<List<Menu>> getAllMenus() {

        List<Menu> menus = menuService.getAllMenus();
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable Long id) {

        menuService.getMenuById(id);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/new")
    public ResponseEntity<List<Menu>> generateMenu(@RequestBody GenerateMenuDto menuDto) {

        if (menuDto.getNumberOfDays() <= 0) {
            throw new IllegalArgumentException("Number of days should be positive");
        }

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(menuDto.getNumberOfDays());

        List<Menu> createdMenu = menuService.generateMenus(menuDto, startDate, endDate);
        return new ResponseEntity<>(createdMenu, HttpStatus.CREATED);
    }

    @PutMapping("/{menuId}/dishes/{dishId}")
    public ResponseEntity<?> replaceDishInMenu(@PathVariable Long menuId, @PathVariable Long dishId) {

        try {
            menuService.replaceDishInMenu(menuId, dishId);
            return ResponseEntity.ok().build();
        } catch (MenuNotFoundException | DishNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{menuId}/dishes/{dishId}")
    public ResponseEntity<?> removeDishFromMenu(@PathVariable Long menuId, @PathVariable Long dishId) {

        try {
            menuService.removeDishFromMenu(menuId, dishId);
            return ResponseEntity.ok().build();
        } catch (MenuNotFoundException | DishNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Menu> deleteMenu(@PathVariable Long id) {

        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userEntity}")
    public ResponseEntity<List<Menu>> getMenusByUser(@PathVariable UserEntity userEntity) {

        List<Menu> menus = menuService.getMenusByUser(userEntity);
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    @GetMapping("/date{date}")
    public ResponseEntity<List<Menu>> getMenuByDate(@PathVariable Date date) {

        List<Menu> menus = menuService.getMenusByDate(date);
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }
}
