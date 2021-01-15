package com.slbruno.carrental.car.controller.docs;

import com.slbruno.carrental.car.dto.CarRequestDTO;
import com.slbruno.carrental.car.dto.CarResponseDTO;
import com.slbruno.carrental.user.dto.AuthenticatedUser;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

public interface CarControllerDocs {

    @ApiOperation(value = "Car creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success car creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or car already registered on system")
    })
    CarResponseDTO create(AuthenticatedUser authenticatedUser, CarRequestDTO carRequestDTO);

    @ApiOperation(value = "Car find by id and user operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success car found"),
            @ApiResponse(code = 404, message = "Car not found error")
    })
    CarResponseDTO findByIdAndUser(AuthenticatedUser authenticatedUser, Long id);

    @ApiOperation(value = "List all cars by a specific authenticated user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Car list found by authenticated user informed")
    })
    List<CarResponseDTO> findAllByUser(AuthenticatedUser authenticatedUser);

    @ApiOperation(value = "Car update operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Car by user successfully updated"),
            @ApiResponse(code = 404, message = "Car not found error"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or car already registered on system")
    })
    CarResponseDTO updateByUser(AuthenticatedUser authenticatedUser, Long carId, CarRequestDTO carRequestDTO);

    @ApiOperation(value = "Car delete operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Car by user successfully deleted"),
            @ApiResponse(code = 404, message = "Car not found error")
    })
    void deleteByIdAndUser(AuthenticatedUser authenticatedUser, Long carId);
}
