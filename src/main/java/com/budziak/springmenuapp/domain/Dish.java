package com.budziak.springmenuapp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String ingredients;
    private String recipe;
    private String category;
}
