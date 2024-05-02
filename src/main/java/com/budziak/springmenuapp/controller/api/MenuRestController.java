package com.budziak.springmenuapp.controller.api;

import com.budziak.springmenuapp.domain.Menu;
import com.budziak.springmenuapp.domain.UserEntity;
import com.budziak.springmenuapp.dto.GenerateMenuDto;
import com.budziak.springmenuapp.dto.MenuDto;
import com.budziak.springmenuapp.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Menu> generateMenu(@RequestBody GenerateMenuDto menuDto) {
        Menu createdMenu = menuService.generateMenu(menuDto);
        return new ResponseEntity<>(createdMenu, HttpStatus.CREATED);
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
