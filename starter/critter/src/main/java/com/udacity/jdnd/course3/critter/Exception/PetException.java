package com.udacity.jdnd.course3.critter.Exception;

public class PetException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PetException(String msg) {
        super(msg);
    }
}
