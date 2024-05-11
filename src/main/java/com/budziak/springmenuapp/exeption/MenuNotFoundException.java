package com.budziak.springmenuapp.exeption;

public class MenuNotFoundException extends RuntimeException {

    public MenuNotFoundException() {
        super("Menu not found");
    }

    public MenuNotFoundException(String message) {
        super(message);
    }

    public MenuNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

