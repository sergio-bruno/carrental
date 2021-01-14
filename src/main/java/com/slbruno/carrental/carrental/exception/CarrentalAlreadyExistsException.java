package com.slbruno.carrental.carrental.exception;

import javax.persistence.EntityExistsException;

public class CarrentalAlreadyExistsException extends EntityExistsException {

    public CarrentalAlreadyExistsException(String name, String code) {
        super(String.format("Car rental with name %s or code %code already exists!", name, code));
    }

    public CarrentalAlreadyExistsException(String name) {
        super(String.format("Car rental with name %s already exists!", name));
    }
}
