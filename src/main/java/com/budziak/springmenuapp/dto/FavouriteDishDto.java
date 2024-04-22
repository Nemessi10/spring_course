package com.budziak.springmenuapp.dto;

import com.budziak.springmenuapp.domain.Dish;
import com.budziak.springmenuapp.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteDishDto {

    private Long id;
    private UserEntity userEntity;
    private Dish dish;
}
