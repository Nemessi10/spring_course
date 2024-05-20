package com.budziak.springmenuapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DishDto {
    private String name;
    private String ingredients;
    private String recipe;
    private String category;
}
