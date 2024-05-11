package com.budziak.springmenuapp.exeption;

public class MenuAlreadyInFavoritesException extends RuntimeException {

    public MenuAlreadyInFavoritesException(String message) {
        super(message);
    }
}
