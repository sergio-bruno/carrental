package com.slbruno.carrental.phone.repository;

import com.slbruno.carrental.phone.entity.Phone;
import com.slbruno.carrental.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    Optional<Phone> findByNumberAndUser(int number, User user);

    Optional<Phone> findByIdAndUser(Long id, User user);

    List<Phone> findAllByUser(User user);

    void deleteByIdAndUser(Long id, User user);
}
