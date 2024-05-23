package com.budziak.springmenuapp.exeption;

public class FavouriteDishAlreadyExistsException extends RuntimeException {

    public FavouriteDishAlreadyExistsException() {
    }

    public FavouriteDishAlreadyExistsException(String message) {
        super(message);
    }

    public FavouriteDishAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public FavouriteDishAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
