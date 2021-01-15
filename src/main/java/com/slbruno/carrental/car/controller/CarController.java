package com.slbruno.carrental.car.controller;

import com.slbruno.carrental.car.controller.docs.CarControllerDocs;
import com.slbruno.carrental.car.dto.CarRequestDTO;
import com.slbruno.carrental.car.dto.CarResponseDTO;
import com.slbruno.carrental.car.service.CarService;
import com.slbruno.carrental.user.dto.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cr/car")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarController implements CarControllerDocs {

    private final CarService carService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarResponseDTO create(@AuthenticationPrincipal AuthenticatedUser authenticatedUser, @RequestBody @Valid CarRequestDTO carRequestDTO) {
        return carService.create(authenticatedUser, carRequestDTO);
    }

    @GetMapping("/{id}")
    public CarResponseDTO findByIdAndUser(@AuthenticationPrincipal AuthenticatedUser authenticatedUser, @PathVariable Long id) {
        return carService.findByIdAndUser(authenticatedUser, id);
    }

    @GetMapping
    public List<CarResponseDTO> findAllByUser(@AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
        return carService.findAllByUser(authenticatedUser);
    }

    @PutMapping("/{id}")
    public CarResponseDTO updateByUser(
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser,
            @PathVariable Long id,
            @RequestBody @Valid CarRequestDTO carRequestDTO) {
        return carService.updateByUser(authenticatedUser, id, carRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByIdAndUser(
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser,
            @PathVariable Long id) {
        carService.deleteByIdAndUser(authenticatedUser, id);
    }
}
