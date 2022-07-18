package com.ldnhat.stdiomanagement.service;

import com.ldnhat.stdiomanagement.entity.UserEntity;

import java.text.ParseException;
import java.util.List;

public interface UserService {

    List<UserEntity> findAll() throws ParseException;
    UserEntity save(UserEntity userEntity);
    UserEntity edit(UserEntity userEntity, Long id);
    void deleteUserById(Long id);
}
