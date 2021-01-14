package com.slbruno.carrental.carrental.controler.docs;

import com.slbruno.carrental.carrental.dto.CarrentalDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api("Carrentals management")
public interface CarrentalControllerDocs {

    @ApiOperation(value = "Carrental creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success carrental creation"),
            @ApiResponse(code = 400, message = "Missing required fileds, wrong field range value or carrental already registered on system")
    })
    CarrentalDTO create(CarrentalDTO carrentalDTO);

    @ApiOperation(value = "Find carrental by name operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success carrental found"),
            @ApiResponse(code = 404, message = "Carrental not found error code")
    })
    CarrentalDTO findByName(String name);

    @ApiOperation(value = "List all registered carrentals")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return all registered carrentals")
    })
    List<CarrentalDTO> findAll();

    @ApiOperation(value = "Delete carrental by id operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success carrental deleted"),
            @ApiResponse(code = 404, message = "Carrental not found error code")
    })
    void delete(Long id);
}
