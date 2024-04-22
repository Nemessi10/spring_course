package com.budziak.springmenuapp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menus")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "breakfast_id")
    private Dish breakfast;

    @ManyToOne
    @JoinColumn(name = "lunch_id")
    private Dish lunch;

    @ManyToOne
    @JoinColumn(name = "dinner_id")
    private Dish dinner;

    private Date date;
}
