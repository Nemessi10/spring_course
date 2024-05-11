package com.budziak.springmenuapp.exeption;

public class DishNotFoundException extends RuntimeException {

    public DishNotFoundException() {
        super("Dish not found");
    }

    public DishNotFoundException(String message) {
        super(message);
    }

    public DishNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

