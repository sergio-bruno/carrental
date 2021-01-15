package com.slbruno.carrental.phone.exception;

import javax.persistence.EntityNotFoundException;

public class PhoneNotFoundException extends EntityNotFoundException {

    public PhoneNotFoundException(String name) {
        super(String.format("Phone with name %s not exists!", name));
    }

    public PhoneNotFoundException(Long id) {
        super(String.format("Phone with id %s not exists!", id));
    }

}
