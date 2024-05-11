package com.budziak.springmenuapp.dto;

import com.budziak.springmenuapp.domain.Dish;
import com.budziak.springmenuapp.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor

public class MenuDto {
    private Long id;
    private Dish breakfast;
    private Dish lunch;
    private Dish dinner;
    private LocalDate date;
}
