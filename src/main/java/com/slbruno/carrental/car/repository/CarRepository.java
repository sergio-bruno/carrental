package com.slbruno.carrental.car.repository;

import com.slbruno.carrental.car.entity.Car;
import com.slbruno.carrental.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByNameAndModelAndUser(String name, String model, User user);

    Optional<Car> findByIdAndUser(Long id, User user);

    List<Car> findAllByUser(User user);

    void deleteByIdAndUser(Long id, User user);
}
