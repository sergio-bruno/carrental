package com.slbruno.carrental.manufacturer.controller.docs;

import com.slbruno.carrental.manufacturer.dto.ManufacturerDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api("Manufacturers management")
public interface ManufacturerControllerDocs {

    @ApiOperation(value = "Manufacturer creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success manufacturer creation"),
            @ApiResponse(code = 400, message = "Missing required fileds, wrong field range value or manufacturer already registered on system")
    })
    ManufacturerDTO create(ManufacturerDTO manufacturerDTO);

    @ApiOperation(value = "Find manufacturer by name operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success manufacturer found"),
            @ApiResponse(code = 404, message = "Manufacturer not found error code")
    })
    ManufacturerDTO findByName(String name);


    @ApiOperation(value = "List all registered manufacturers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return all registered manufacturers")
    })
    List<ManufacturerDTO> findAll();

    @ApiOperation(value = "Delete manufacturer by id operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success manufacturer deleted"),
            @ApiResponse(code = 404, message = "Manufacturer not found error code")
    })
    void delete(Long id);
}
