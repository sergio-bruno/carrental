package com.slbruno.carrental.manufacturer.exception;

import javax.persistence.EntityExistsException;

public class ManufacturerAlreadyExistsException extends EntityExistsException {

    public ManufacturerAlreadyExistsException(String name, String code) {
        super(String.format("User with name %s or code %code already exists!", name, code));
    }

    public ManufacturerAlreadyExistsException(String name) {
        super(String.format("User with name %s already exists!", name));
    }
}
