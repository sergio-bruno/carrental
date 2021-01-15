package com.slbruno.carrental.phone.exception;

import javax.persistence.EntityExistsException;

public class PhoneAlreadyExistsException extends EntityExistsException {

    public PhoneAlreadyExistsException(Integer number, String username) {
        super(String.format("Phone with name %s, number %s for user " +
                "%s already registered!", number, username));
    }
}
