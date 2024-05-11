package com.budziak.springmenuapp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateMenuDto {
    private Long userId;
    private int numberOfDays;
}
