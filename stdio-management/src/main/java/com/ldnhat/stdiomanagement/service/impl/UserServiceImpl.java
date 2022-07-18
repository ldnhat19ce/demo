package com.ldnhat.stdiomanagement.service.impl;

import com.ldnhat.stdiomanagement.entity.UserEntity;
import com.ldnhat.stdiomanagement.repository.UserRepository;
import com.ldnhat.stdiomanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> findAll() throws ParseException {
        return userRepository.findAll();
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity edit(UserEntity userEntity, Long id) {
        UserEntity updateUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("id not found"));
        updateUser.setName(userEntity.getName());
        updateUser.setSurname(userEntity.getSurname());
        updateUser.setBirthday(userEntity.getBirthday());
        updateUser.setAddress(userEntity.getAddress());
        return userRepository.save(updateUser);
    }

    @Override
    public void deleteUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("id not found"));
        userRepository.delete(userEntity);
    }
}
