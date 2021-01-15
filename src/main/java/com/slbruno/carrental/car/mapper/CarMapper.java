package com.slbruno.carrental.car.mapper;

import com.slbruno.carrental.car.dto.CarRequestDTO;
import com.slbruno.carrental.car.dto.CarResponseDTO;
import com.slbruno.carrental.car.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    Car toModel(CarRequestDTO bookRequestDTO);

    Car toModel(CarResponseDTO bookRequestDTO);

    CarResponseDTO toDTO(Car bookDTO);
}
