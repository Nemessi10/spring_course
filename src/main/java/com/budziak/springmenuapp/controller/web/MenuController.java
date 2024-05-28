package com.budziak.springmenuapp.controller.web;

import com.budziak.springmenuapp.domain.Menu;
import com.budziak.springmenuapp.dto.GenerateMenuDto;
import com.budziak.springmenuapp.dto.MenuDto;
import com.budziak.springmenuapp.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/menus")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public String getAllMenus(Model model) {
        List<Menu> menus = menuService.getAllMenus();
        model.addAttribute("menus", menus);
        return "menus";
    }
}
