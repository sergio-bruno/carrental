package com.slbruno.carrental.phone.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slbruno.carrental.phone.dto.PhoneRequestDTO;
import com.slbruno.carrental.phone.dto.PhoneResponseDTO;
import com.slbruno.carrental.phone.entity.Phone;
import com.slbruno.carrental.phone.exception.PhoneAlreadyExistsException;
import com.slbruno.carrental.phone.exception.PhoneNotFoundException;
import com.slbruno.carrental.phone.mapper.PhoneMapper;
import com.slbruno.carrental.phone.repository.PhoneRepository;
import com.slbruno.carrental.user.dto.AuthenticatedUser;
import com.slbruno.carrental.user.entity.User;
import com.slbruno.carrental.user.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PhoneService {

    private final PhoneRepository phoneRepository;

    private final UserService userService;

    private final PhoneMapper phoneMapper = PhoneMapper.INSTANCE;

    public PhoneResponseDTO create(AuthenticatedUser authenticatedUser, PhoneRequestDTO phoneRequestDTO) {
        User foundAuthenticatedUser = userService.verifyAndGetUserIfExists(authenticatedUser.getUsername());
        verifyIfIsAlreadyRegistered(phoneRequestDTO, foundAuthenticatedUser);

        Phone phoneToSave = phoneMapper.toModel(phoneRequestDTO);
        phoneToSave.setUser(foundAuthenticatedUser);
        Phone savedPhone = phoneRepository.save(phoneToSave);
        return phoneMapper.toDTO(savedPhone);
    }

    public PhoneResponseDTO findByIdAndUser(AuthenticatedUser authenticatedUser, Long carId) {
        User foundAuthenticatedUser = userService.verifyAndGetUserIfExists(authenticatedUser.getUsername());
        return phoneRepository.findByIdAndUser(carId, foundAuthenticatedUser)
                .map(phoneMapper::toDTO)
                .orElseThrow(() -> new PhoneNotFoundException(carId));
    }

    public List<PhoneResponseDTO> findAllByUser(AuthenticatedUser authenticatedUser) {
        User foundAuthenticatedUser = userService.verifyAndGetUserIfExists(authenticatedUser.getUsername());
        return phoneRepository.findAllByUser(foundAuthenticatedUser)
                .stream()
                .map(phoneMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PhoneResponseDTO updateByUser(AuthenticatedUser authenticatedUser, Long phoneId, PhoneRequestDTO phoneRequestDTO) {
        User foundAuthenticatedUser = userService.verifyAndGetUserIfExists(authenticatedUser.getUsername());
        Phone foundPhone = findByIdAndUser(phoneId, foundAuthenticatedUser);

        Phone phoneToUpdate = phoneMapper.toModel(phoneRequestDTO);
        phoneToUpdate.setId(foundPhone.getId());
        phoneToUpdate.setUser(foundAuthenticatedUser);
        phoneToUpdate.setCreatedBy(foundPhone.getCreatedBy());
        phoneToUpdate.setCreatedDate(foundPhone.getCreatedDate());
        Phone savedPhone = phoneRepository.save(phoneToUpdate);
        return phoneMapper.toDTO(savedPhone);
    }

    @Transactional
    public void deleteByIdAndUser(AuthenticatedUser authenticatedUser, Long phoneId) {
        User foundAuthenticatedUser = userService.verifyAndGetUserIfExists(authenticatedUser.getUsername());
        Phone foundPhoneToDelete = findByIdAndUser(phoneId, foundAuthenticatedUser);
        phoneRepository.deleteByIdAndUser(foundPhoneToDelete.getId(), foundAuthenticatedUser);
    }

    private Phone findByIdAndUser(Long carId, User foundAuthenticatedUser) {
        return phoneRepository.findByIdAndUser(carId, foundAuthenticatedUser)
                .orElseThrow(() -> new PhoneNotFoundException(carId));
    }

    private void verifyIfIsAlreadyRegistered(PhoneRequestDTO phoneRequestDTO, User foundAuthenticatedUser) {
        phoneRepository.findByNumberAndUser(phoneRequestDTO.getNumber(), foundAuthenticatedUser)
                .ifPresent(duplicatedPhone -> {
                    throw new PhoneAlreadyExistsException(phoneRequestDTO.getNumber(), foundAuthenticatedUser.getName());
                });
    }

}
