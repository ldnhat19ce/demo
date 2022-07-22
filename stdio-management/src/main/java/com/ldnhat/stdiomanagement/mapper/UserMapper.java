package com.ldnhat.stdiomanagement.mapper;

import com.ldnhat.stdiomanagement.dto.UserDto;
import com.ldnhat.stdiomanagement.entity.UserEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    List<UserDto> mapUserEntitiesToUserDtos(List<UserEntity> userEntities);
    UserDto mapUserEntityToUserDto(UserEntity userEntity);
    UserEntity mapUserDtoToUserEntity(UserDto userDto);


}
