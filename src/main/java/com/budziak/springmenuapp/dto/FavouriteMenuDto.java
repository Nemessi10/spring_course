package com.budziak.springmenuapp.dto;

import com.budziak.springmenuapp.domain.Menu;
import com.budziak.springmenuapp.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteMenuDto {

    private Long id;
    private UserEntity userEntity;
    private Menu menu;
}
