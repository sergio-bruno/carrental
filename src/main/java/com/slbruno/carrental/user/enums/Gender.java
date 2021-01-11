package com.slbruno.carrental.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    MALE("Male"),
    FEMALE("Female");

    private String description;
}
