package com.kantox.checkout.core.exceptions;

public class DataNotFoundException extends Exception {

    private final String name;
    private final String id;

    public DataNotFoundException(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Not found "+name+" with id "+id;
    }
}
