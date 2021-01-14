package com.slbruno.carrental.carrental.exception;

import javax.persistence.EntityNotFoundException;

public class CarrentalNotFoundException extends EntityNotFoundException {

    public CarrentalNotFoundException(String name) {
        super(String.format("Car rental with name %s not exists!", name));
    }

    public CarrentalNotFoundException(Long id) {
        super(String.format("Car rental with id %s not exists!", id));
    }
}
