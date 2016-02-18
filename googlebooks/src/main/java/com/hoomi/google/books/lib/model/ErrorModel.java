package com.hoomi.google.books.lib.model;

/**
 * Created by hoomanostovari on 18/02/2016.
 */
public class ErrorModel {
    private final String message;

    public ErrorModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
