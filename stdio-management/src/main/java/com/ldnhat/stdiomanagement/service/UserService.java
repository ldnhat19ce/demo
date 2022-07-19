package com.ldnhat.stdiomanagement.service;

import com.ldnhat.stdiomanagement.dto.UserDto;
import com.ldnhat.stdiomanagement.entity.UserEntity;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

public interface UserService {

    List<UserDto> findAll();
    UserDto save(UserDto userDto);
    UserDto edit(UserDto userDto, Long id);
    void deleteUserById(Long id);
    UserEntity findById(Long id);
}
