package com.slbruno.carrental.phone.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.slbruno.carrental.entity.Auditable;
import com.slbruno.carrental.user.entity.User;

import lombok.Data;

@Data
@Entity
public class Phone extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "integer default 0")
    private int number;
    
    @Column(columnDefinition = "integer default 0")
    private int areaCode;
    
    @Column(nullable = false, length = 10)
    private String countryCode;
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    private User user;
}
