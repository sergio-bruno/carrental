package com.slbruno.carrental.car.dto;

import com.slbruno.carrental.manufacturer.dto.ManufacturerDTO;
import com.slbruno.carrental.carrental.dto.CarrentalDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResponseDTO {

    private Long id;

    private String licensePlate;
    
    private String name;
    
    private String model;

    private Integer year;
    
    private CarrentalDTO carrental;

    private ManufacturerDTO manufacturer;

}
