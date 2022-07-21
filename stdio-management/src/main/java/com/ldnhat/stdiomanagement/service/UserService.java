package com.ldnhat.stdiomanagement.service;

import com.ldnhat.stdiomanagement.dto.UserDto;
import com.ldnhat.stdiomanagement.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserDto> findAll();
    UserDto save(UserDto userDto);
    UserDto edit(UserDto userDto, Long id);
    void deleteUserById(Long id);
    UserEntity findById(Long id);
    UserDetails loadUsernameById(Long id);

}
