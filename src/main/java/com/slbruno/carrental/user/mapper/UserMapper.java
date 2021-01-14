package com.slbruno.carrental.user.mapper;

import com.slbruno.carrental.user.dto.UserDTO;
import com.slbruno.carrental.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toModel(UserDTO userDTO);

    UserDTO toDTO(User user);
}
