package com.slbruno.carrental.phone.controller.docs;

import com.slbruno.carrental.phone.dto.PhoneRequestDTO;
import com.slbruno.carrental.phone.dto.PhoneResponseDTO;
import com.slbruno.carrental.user.dto.AuthenticatedUser;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

public interface PhoneControllerDocs {

    @ApiOperation(value = "Phone creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success phone creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or phone already registered on system")
    })
    PhoneResponseDTO create(AuthenticatedUser authenticatedUser, PhoneRequestDTO phoneRequestDTO);

    @ApiOperation(value = "Phone find by id and user operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success phone found"),
            @ApiResponse(code = 404, message = "Phone not found error")
    })
    PhoneResponseDTO findByIdAndUser(AuthenticatedUser authenticatedUser, Long id);

    @ApiOperation(value = "List all phones by a specific authenticated user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Phone list found by authenticated user informed")
    })
    List<PhoneResponseDTO> findAllByUser(AuthenticatedUser authenticatedUser);

    @ApiOperation(value = "Phone update operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Phone by user successfully updated"),
            @ApiResponse(code = 404, message = "Phone not found error"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or phone already registered on system")
    })
    PhoneResponseDTO updateByUser(AuthenticatedUser authenticatedUser, Long phoneId, PhoneRequestDTO phoneRequestDTO);

    @ApiOperation(value = "Phone delete operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Phone by user successfully deleted"),
            @ApiResponse(code = 404, message = "Phone not found error")
    })
    void deleteByIdAndUser(AuthenticatedUser authenticatedUser, Long phoneId);
	
}
