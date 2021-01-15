package com.slbruno.carrental.car.exception;

import javax.persistence.EntityNotFoundException;

public class CarNotFoundException extends EntityNotFoundException {

    public CarNotFoundException(String name) {
        super(String.format("Car with name %s not exists!", name));
    }

    public CarNotFoundException(Long id) {
        super(String.format("Car with id %s not exists!", id));
    }
}
