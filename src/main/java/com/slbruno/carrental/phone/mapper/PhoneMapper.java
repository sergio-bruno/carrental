package com.slbruno.carrental.phone.mapper;

import com.slbruno.carrental.phone.dto.PhoneRequestDTO;
import com.slbruno.carrental.phone.dto.PhoneResponseDTO;
import com.slbruno.carrental.phone.entity.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhoneMapper {

	PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);

	Phone toModel(PhoneRequestDTO phoneRequestDTO);

	Phone toModel(PhoneResponseDTO phoneRequestDTO);

	PhoneResponseDTO toDTO(Phone phoneDTO);
}

