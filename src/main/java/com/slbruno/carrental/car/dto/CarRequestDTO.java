package com.slbruno.carrental.car.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRequestDTO {

    private Long id;

    @NotNull
    @Size(max = 20)
    private String licensePlate;
    
    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String name;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String model;
    
    @NotNull
    private Integer year;

    @NotNull
    private Long carrentalId;

    @NotNull
    private Long manufacturerId;
    
}
