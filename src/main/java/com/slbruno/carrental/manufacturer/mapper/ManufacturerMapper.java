package com.slbruno.carrental.manufacturer.mapper;

import com.slbruno.carrental.manufacturer.dto.ManufacturerDTO;
import com.slbruno.carrental.manufacturer.entity.Manufacturer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ManufacturerMapper {

    ManufacturerMapper INSTANCE = Mappers.getMapper(ManufacturerMapper.class);

    Manufacturer toModel(ManufacturerDTO manufacturerDTO);

    ManufacturerDTO toDTO(Manufacturer manufacturer);
}
