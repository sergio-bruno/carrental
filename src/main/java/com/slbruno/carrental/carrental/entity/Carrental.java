package com.slbruno.carrental.carrental.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.slbruno.carrental.car.entity.Car;
import com.slbruno.carrental.entity.Auditable;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Carrental extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDate foundationDate;

    @OneToMany(mappedBy = "carrental", fetch = FetchType.LAZY)
    private List<Car> cars;
}
