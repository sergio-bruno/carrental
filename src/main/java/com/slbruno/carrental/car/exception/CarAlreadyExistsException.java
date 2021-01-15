package com.slbruno.carrental.car.exception;

import javax.persistence.EntityExistsException;

public class CarAlreadyExistsException extends EntityExistsException {

    public CarAlreadyExistsException(String name, String isbn, String username) {
        super(String.format("Car with name %s, ISBN %s for user " +
                "%s already registered!", name, isbn, username));
    }
}
