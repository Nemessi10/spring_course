package com.budziak.springmenuapp.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<UserRole> userRoles;


    @OneToMany(mappedBy = "userEntity")
    private Set<Menu> menus;

    @OneToMany(mappedBy = "userEntity")
    private Set<FavouriteDish> favouriteDishes;

    @OneToMany(mappedBy = "userEntity")
    private Set<FavouriteMenu> favouriteMenus;
}
