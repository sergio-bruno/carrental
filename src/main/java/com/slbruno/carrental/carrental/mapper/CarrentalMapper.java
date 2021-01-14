package com.slbruno.carrental.carrental.mapper;

import com.slbruno.carrental.carrental.dto.CarrentalDTO;
import com.slbruno.carrental.carrental.entity.Carrental;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarrentalMapper {

    CarrentalMapper INSTANCE = Mappers.getMapper(CarrentalMapper.class);

    Carrental toModel(CarrentalDTO carrentalDTO);

    CarrentalDTO toDTO(Carrental carrental);
}
