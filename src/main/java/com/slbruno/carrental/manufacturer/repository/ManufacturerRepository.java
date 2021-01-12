package com.slbruno.carrental.manufacturer.repository;

import com.slbruno.carrental.manufacturer.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    Optional<Manufacturer> findByName(String name);
}
