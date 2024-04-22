package com.budziak.springmenuapp.service;

import com.budziak.springmenuapp.domain.Menu;
import com.budziak.springmenuapp.domain.UserEntity;
import com.budziak.springmenuapp.dto.MenuDto;
import com.budziak.springmenuapp.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final UserService userService;

    @Autowired
    public MenuService(MenuRepository menuRepository, UserService userService) {
        this.menuRepository = menuRepository;
        this.userService = userService;
    }

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Optional<Menu> getMenuById(Long id) {
        return menuRepository.findById(id);
    }

    public List<Menu> getMenusByUser(UserEntity userEntity) {
        return menuRepository.findByUserEntity(userEntity);
    }

    public List<Menu> getMenusByDate(Date date) {
        return menuRepository.findByDate(date);
    }

    public Menu createMenu(MenuDto menuDto, Long userId) {
        UserEntity userEntity = userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Menu menu = Menu.builder()
                .userEntity(userEntity)
                .breakfast(menuDto.getBreakfast())
                .lunch(menuDto.getLunch())
                .dinner(menuDto.getDinner())
                .date(menuDto.getDate())
                .build();

        return menuRepository.save(menu);
    }

    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }
}
