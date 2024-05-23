package com.budziak.springmenuapp.controller.api;

import com.budziak.springmenuapp.domain.Menu;
import com.budziak.springmenuapp.dto.GenerateMenuDto;
import com.budziak.springmenuapp.exeption.*;
import com.budziak.springmenuapp.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Menu> getAllMenus() {

        List<Menu> menus = menuService.getAllMenus();

        if (menus == null)
            throw new ResourceNotFoundException();
        else if (menus.isEmpty())
            throw new NoContentException();
        else return menus;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Menu getMenuById(@PathVariable Long id) {

        return menuService.getMenuById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu with id " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Menu> generateMenu(@RequestBody GenerateMenuDto menuDto) {

        if (menuDto.getNumberOfDays() <= 0) {
            throw new IllegalArgumentException("Number of days should be positive");
        }

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(menuDto.getNumberOfDays());

        return menuService.generateMenus(menuDto, startDate, endDate);
    }

    @PutMapping("/{menuId}/dishes/{dishId}")
    @ResponseStatus(HttpStatus.OK)
    public void replaceDishInMenu(@PathVariable Long menuId, @PathVariable Long dishId) {

        try {
            menuService.replaceDishInMenu(menuId, dishId);
        } catch (MenuNotFoundException | DishNotFoundException e) {
            throw new ResourceNotFoundException();
        } catch (Exception e) {
            throw new InternalServerErrorException("An unexpected error occurred");
        }
    }

    @DeleteMapping("/{menuId}/dishes/{dishId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeDishFromMenu(@PathVariable Long menuId, @PathVariable Long dishId) {

        try {
            menuService.removeDishFromMenu(menuId, dishId);
        } catch (MenuNotFoundException | DishNotFoundException e) {
            throw new ResourceNotFoundException();
        } catch (Exception e) {
            throw new InternalServerErrorException("An unexpected error occurred");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMenu(@PathVariable Long id) {

        menuService.deleteMenu(id);
    }
}
