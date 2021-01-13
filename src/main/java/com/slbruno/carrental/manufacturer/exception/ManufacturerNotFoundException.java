package com.slbruno.carrental.manufacturer.exception;

import javax.persistence.EntityNotFoundException;

public class ManufacturerNotFoundException extends EntityNotFoundException {

    public ManufacturerNotFoundException(String name) {
        super(String.format("Manufacturer with name %s not exists!", name));
    }

    public ManufacturerNotFoundException(Long id) {
        super(String.format("Manufacturer with id %s not exists!", id));
    }
}
