package com.slbruno.carrental.car.service;

import com.slbruno.carrental.manufacturer.entity.Manufacturer;
import com.slbruno.carrental.manufacturer.service.ManufacturerService;
import com.slbruno.carrental.car.dto.CarRequestDTO;
import com.slbruno.carrental.car.dto.CarResponseDTO;
import com.slbruno.carrental.car.entity.Car;
import com.slbruno.carrental.car.exception.CarAlreadyExistsException;
import com.slbruno.carrental.car.exception.CarNotFoundException;
import com.slbruno.carrental.car.mapper.CarMapper;
import com.slbruno.carrental.car.repository.CarRepository;
import com.slbruno.carrental.carrental.entity.Carrental;
import com.slbruno.carrental.carrental.service.CarrentalService;
import com.slbruno.carrental.user.dto.AuthenticatedUser;
import com.slbruno.carrental.user.entity.User;
import com.slbruno.carrental.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarService {

    private final CarRepository carRepository;

    private final UserService userService;

    private final ManufacturerService manufacturerService;

    private final CarrentalService carrentalService;

    private final CarMapper carMapper = CarMapper.INSTANCE;

    public CarResponseDTO create(AuthenticatedUser authenticatedUser, CarRequestDTO carRequestDTO) {
        User foundAuthenticatedUser = userService.verifyAndGetUserIfExists(authenticatedUser.getUsername());
        verifyIfIsAlreadyRegistered(carRequestDTO, foundAuthenticatedUser);
        Manufacturer foundManufacturer = manufacturerService.verifyAndGetIfExists(carRequestDTO.getManufacturerId());
        Carrental foundCarrental = carrentalService.verifyAndGetIfExists(carRequestDTO.getCarrentalId());

        Car carToSave = carMapper.toModel(carRequestDTO);
        carToSave.setUser(foundAuthenticatedUser);
        carToSave.setManufacturer(foundManufacturer);
        carToSave.setCarrental(foundCarrental);
        Car savedCar = carRepository.save(carToSave);
        return carMapper.toDTO(savedCar);
    }

    public CarResponseDTO findByIdAndUser(AuthenticatedUser authenticatedUser, Long carId) {
        User foundAuthenticatedUser = userService.verifyAndGetUserIfExists(authenticatedUser.getUsername());
        return carRepository.findByIdAndUser(carId, foundAuthenticatedUser)
                .map(carMapper::toDTO)
                .orElseThrow(() -> new CarNotFoundException(carId));
    }

    public List<CarResponseDTO> findAllByUser(AuthenticatedUser authenticatedUser) {
        User foundAuthenticatedUser = userService.verifyAndGetUserIfExists(authenticatedUser.getUsername());
        return carRepository.findAllByUser(foundAuthenticatedUser)
                .stream()
                .map(carMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CarResponseDTO updateByUser(AuthenticatedUser authenticatedUser, Long carId, CarRequestDTO carRequestDTO) {
        User foundAuthenticatedUser = userService.verifyAndGetUserIfExists(authenticatedUser.getUsername());
        Car foundCar = findByIdAndUser(carId, foundAuthenticatedUser);

        Manufacturer foundManufacturer = manufacturerService.verifyAndGetIfExists(carRequestDTO.getManufacturerId());
        Carrental foundCarrental = carrentalService.verifyAndGetIfExists(carRequestDTO.getCarrentalId());

        Car carToUpdate = carMapper.toModel(carRequestDTO);
        carToUpdate.setId(foundCar.getId());
        carToUpdate.setUser(foundAuthenticatedUser);
        carToUpdate.setManufacturer(foundManufacturer);
        carToUpdate.setCarrental(foundCarrental);
        carToUpdate.setCreatedBy(foundCar.getCreatedBy());
        carToUpdate.setCreatedDate(foundCar.getCreatedDate());
        Car savedCar = carRepository.save(carToUpdate);
        return carMapper.toDTO(savedCar);
    }

    @Transactional
    public void deleteByIdAndUser(AuthenticatedUser authenticatedUser, Long carId) {
        User foundAuthenticatedUser = userService.verifyAndGetUserIfExists(authenticatedUser.getUsername());
        Car foundCarToDelete = findByIdAndUser(carId, foundAuthenticatedUser);
        carRepository.deleteByIdAndUser(foundCarToDelete.getId(), foundAuthenticatedUser);
    }

    private Car findByIdAndUser(Long carId, User foundAuthenticatedUser) {
        return carRepository.findByIdAndUser(carId, foundAuthenticatedUser)
                .orElseThrow(() -> new CarNotFoundException(carId));
    }

    private void verifyIfIsAlreadyRegistered(CarRequestDTO carRequestDTO, User foundAuthenticatedUser) {
        carRepository.findByNameAndLicensePlateAndUser(carRequestDTO.getName(), carRequestDTO.getLicensePlate(), foundAuthenticatedUser)
                .ifPresent(duplicatedCar -> {
                    throw new CarAlreadyExistsException(carRequestDTO.getName(), carRequestDTO.getLicensePlate(), foundAuthenticatedUser.getName());
                });
    }
}
