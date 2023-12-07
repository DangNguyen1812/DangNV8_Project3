package com.udacity.jdnd.course3.critter.Exception;

public class CustomerException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CustomerException(String msg) {
        super(msg);
    }
}
