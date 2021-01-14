package com.slbruno.carrental.carrental.service;

import com.slbruno.carrental.carrental.dto.CarrentalDTO;
import com.slbruno.carrental.carrental.entity.Carrental;
import com.slbruno.carrental.carrental.exception.CarrentalAlreadyExistsException;
import com.slbruno.carrental.carrental.exception.CarrentalNotFoundException;
import com.slbruno.carrental.carrental.mapper.CarrentalMapper;
import com.slbruno.carrental.carrental.repository.CarrentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarrentalService {

    private final static CarrentalMapper carrentalMapper = CarrentalMapper.INSTANCE;

    private final CarrentalRepository carrentalRepository;

    public CarrentalDTO create(CarrentalDTO carrentalDTO) {
        verifyIfExists(carrentalDTO.getName());
        Carrental carrentalToCreate = carrentalMapper.toModel(carrentalDTO);
        Carrental createdCarrental = carrentalRepository.save(carrentalToCreate);
        return carrentalMapper.toDTO(createdCarrental);
    }

    public CarrentalDTO findByName(String name) {
        Carrental foundCarrental = carrentalRepository.findByName(name)
                .orElseThrow(() -> new CarrentalNotFoundException(name));
        return carrentalMapper.toDTO(foundCarrental);
    }

    public List<CarrentalDTO> findAll() {
        return carrentalRepository.findAll()
                .stream()
                .map(carrentalMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Carrental verifyAndGetIfExists(Long id) {
        return carrentalRepository.findById(id)
                .orElseThrow(() -> new CarrentalNotFoundException(id));
    }

    public void delete(Long id) {
        verifyAndGetIfExists(id);
        carrentalRepository.deleteById(id);
    }

    private void verifyIfExists(String carrentalName) {
        carrentalRepository.findByName(carrentalName)
                .ifPresent(carrental -> {
                    throw new CarrentalAlreadyExistsException(carrentalName);
                });
    }
}
