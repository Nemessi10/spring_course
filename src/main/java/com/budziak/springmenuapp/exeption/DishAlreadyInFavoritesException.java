package com.budziak.springmenuapp.exeption;

public class DishAlreadyInFavoritesException extends RuntimeException {

    public DishAlreadyInFavoritesException(String message) {
        super(message);
    }
}
