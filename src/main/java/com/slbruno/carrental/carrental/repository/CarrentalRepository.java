package com.slbruno.carrental.carrental.repository;

import com.slbruno.carrental.carrental.entity.Carrental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarrentalRepository extends JpaRepository<Carrental, Long> {

    Optional<Carrental> findByName(String name);
}
