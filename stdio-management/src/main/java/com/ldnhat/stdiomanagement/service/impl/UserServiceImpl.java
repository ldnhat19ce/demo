package com.ldnhat.stdiomanagement.service.impl;

import com.ldnhat.stdiomanagement.common.constant.Constant;
import com.ldnhat.stdiomanagement.common.custom.CustomUserDetails;
import com.ldnhat.stdiomanagement.dto.UserDto;
import com.ldnhat.stdiomanagement.entity.UserEntity;
import com.ldnhat.stdiomanagement.exception.ResourceNotFoundException;
import com.ldnhat.stdiomanagement.mapper.UserMapper;
import com.ldnhat.stdiomanagement.repository.UserRepository;
import com.ldnhat.stdiomanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;

    }

    @Override
    public List<UserDto> findAll() {
        return userMapper.mapUserEntitiesToUserDtos(userRepository.findAll());
    }

    @Override
    public UserDto save(UserDto userDto) {
        UserEntity userEntity = userMapper.mapUserDtoToUserEntity(userDto);
        return userMapper.mapUserEntityToUserDto(userRepository.save(userEntity));
    }

    @Override
    public UserDto edit(UserDto userDto, Long id) {
        UserEntity updateUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
        updateUser.setName(userDto.getName());
        updateUser.setSurname(userDto.getSurname());
        updateUser.setBirthday(userDto.getBirthday());
        updateUser.setAddress(userDto.getAddress());

        return userMapper.INSTANCE.mapUserEntityToUserDto(updateUser);
    }

    @Override
    public void deleteUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
        userRepository.delete(userEntity);
    }

    @Override
    public UserEntity findById(Long id) {

        return userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("user", "id", id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null){
            throw new UsernameNotFoundException(username+" not found");
        }
        return new CustomUserDetails(userEntity);
    }

    @Override
    public UserDetails loadUsernameById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException(Constant.USER_NOT_FOUND + id)
        );

        return new CustomUserDetails(userEntity);
    }
}
