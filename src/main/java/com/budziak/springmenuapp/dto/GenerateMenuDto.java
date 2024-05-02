package com.budziak.springmenuapp.dto;

import com.budziak.springmenuapp.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateMenuDto {
    private Long userId;
    private Date date;
}
