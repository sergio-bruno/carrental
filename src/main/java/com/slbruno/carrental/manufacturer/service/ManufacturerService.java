package com.slbruno.carrental.manufacturer.service;

import com.slbruno.carrental.manufacturer.dto.ManufacturerDTO;
import com.slbruno.carrental.manufacturer.entity.Manufacturer;
import com.slbruno.carrental.manufacturer.exception.ManufacturerAlreadyExistsException;
import com.slbruno.carrental.manufacturer.exception.ManufacturerNotFoundException;
import com.slbruno.carrental.manufacturer.mapper.ManufacturerMapper;
import com.slbruno.carrental.manufacturer.repository.ManufacturerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ManufacturerService {

    private final static ManufacturerMapper manufacturerMapper = ManufacturerMapper.INSTANCE;

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerDTO create(ManufacturerDTO manufacturerDTO) {
        verifyIfExists(manufacturerDTO.getName());
        Manufacturer manufacturerToCreate = manufacturerMapper.toModel(manufacturerDTO);
        Manufacturer createdManufacturer = manufacturerRepository.save(manufacturerToCreate);
        return manufacturerMapper.toDTO(createdManufacturer);
    }

    public ManufacturerDTO findByName(String name) {
        Manufacturer foundManufacturer = manufacturerRepository.findByName(name)
                .orElseThrow(() -> new ManufacturerNotFoundException(name));
        return manufacturerMapper.toDTO(foundManufacturer);
    }

    public List<ManufacturerDTO> findAll() {
        return manufacturerRepository.findAll()
                .stream()
                .map(manufacturerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Manufacturer verifyAndGetIfExists(Long id) {
        return manufacturerRepository.findById(id)
                .orElseThrow(() -> new ManufacturerNotFoundException(id));
    }

    public void delete(Long id) {
        verifyAndGetIfExists(id);
        manufacturerRepository.deleteById(id);
    }

    private void verifyIfExists(String manufacturerName) {
        manufacturerRepository.findByName(manufacturerName)
                .ifPresent(manufacturer -> {
                    throw new ManufacturerAlreadyExistsException(manufacturerName);
                });
    }
}
