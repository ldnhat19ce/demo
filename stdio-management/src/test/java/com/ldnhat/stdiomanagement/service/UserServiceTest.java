package com.ldnhat.stdiomanagement.service;

import com.ldnhat.stdiomanagement.dto.UserDto;
import com.ldnhat.stdiomanagement.entity.UserEntity;
import com.ldnhat.stdiomanagement.mapper.UserMapper;
import com.ldnhat.stdiomanagement.repository.UserRepository;
import com.ldnhat.stdiomanagement.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;


    private UserDto userDto1;
    private UserDto userDto2;

    @BeforeEach
    public void setup(){
        userDto1 = UserDto.builder().id(1L)
                    .surname("lenhat")
                    .name("123")
                    .build();

        userDto2 = UserDto.builder().id(2L)
                    .surname("test")
                    .name("456")
                    .build();
    }

    @Test
    public void givenGetAll_WillReturnList(){

        //create mock data
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(userDto1);
        userDtos.add(userDto2);

        //define behavior of repository
        given(userMapper.mapUserEntitiesToUserDtos(userRepository.findAll())).willReturn(
                userDtos
        );

        //call service method
        List<UserDto> actualUsers = userService.findAll();

        //assert the result
        assertThat(actualUsers).isNotNull();
        assertThat(actualUsers.size()).isEqualTo(2);
        assertThat(actualUsers.size()).isEqualTo(userDtos.size());
    }

    @Test
    public void givenUserDtoObject_whenSaveUserEntity_thenReturnUserDtoObject(){
        UserDto userDto3 = UserDto.builder()
                .surname("le duc")
                .name("nhat")
                .build();

        UserEntity userEntity = userMapper.INSTANCE.mapUserDtoToUserEntity(userDto3);
        given(userMapper.mapUserEntityToUserDto(userRepository.save(userEntity)))
                .willReturn(userDto3);

        UserDto actualUserDto = userService.save(userDto3);
        assertThat(actualUserDto).isNotNull();
        assertThat(actualUserDto.getName()).isEqualTo(userDto3.getName());
    }

    @Test
    public void givenUserObjectAndId_whenUpdateUser_thenReturnUpdatedUser(){
        UserDto userDto4 = UserDto.builder()
                .surname("le duc")
                .name("nhat")
                .address("123")
                .build();
        UserEntity userEntity = userMapper.INSTANCE.mapUserDtoToUserEntity(userDto4);
        given(userRepository.findById(1L)).willReturn(Optional.of(userEntity));

        userDto4.setName("test");
        userDto4.setSurname("12345");
        userDto4.setAddress("t333");

        UserDto updatedUser = userService.edit(userDto4, 1L);

        assertThat(updatedUser.getName()).isEqualTo("test");
    }

    @Test
    public void givenUserId_whenDeleteUser_thenNothing(){
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .build();
        given(userRepository.findById(1L)).willReturn(Optional.of(userEntity));

        willDoNothing().given(userRepository).delete(userEntity);

        userService.deleteUserById(1L);

        verify(userRepository).delete(userEntity);
    }
}
