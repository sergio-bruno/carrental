package com.slbruno.carrental.car.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.slbruno.carrental.carrental.entity.Carrental;
import com.slbruno.carrental.entity.Auditable;
import com.slbruno.carrental.manufacturer.entity.Manufacturer;
import com.slbruno.carrental.user.entity.User;

import lombok.Data;

@Data
@Entity
public class Car extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String license_plate;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private String model;

    @Column(columnDefinition = "integer default 0")
    private int year;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Carrental carrental;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Manufacturer manufacturer;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private User user;
}
