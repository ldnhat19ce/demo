package com.ldnhat.stdiomanagement.mapper;

import com.ldnhat.stdiomanagement.dto.UserDto;
import com.ldnhat.stdiomanagement.entity.UserEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    List<UserDto> mapUserEntitiesToUserDtos(List<UserEntity> userEntities);
    UserDto mapUserEntityToUserDto(UserEntity userEntity);
    UserEntity mapUserDtoToUserEntity(UserDto userDto);
    List<UserEntity> mapUserDtosToUserEntities(List<UserDto> userDtos);


}
