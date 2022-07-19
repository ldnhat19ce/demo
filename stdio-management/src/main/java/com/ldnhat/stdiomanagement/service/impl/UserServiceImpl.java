package com.ldnhat.stdiomanagement.service.impl;

import com.ldnhat.stdiomanagement.dto.UserDto;
import com.ldnhat.stdiomanagement.entity.UserEntity;
import com.ldnhat.stdiomanagement.exception.ResourceNotFoundException;
import com.ldnhat.stdiomanagement.repository.UserRepository;
import com.ldnhat.stdiomanagement.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> userDtos = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll();

        for (UserEntity userEntity : userEntities){
            UserDto userDto = mapToDto(userEntity);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public UserDto save(UserDto userDto) {
        UserEntity userEntity = mapToEntity(userDto);
        return mapToDto(userRepository.save(userEntity));
    }

    @Override
    public UserDto edit(UserDto userDto, Long id) {
        UserEntity updateUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", id));

        updateUser.setName(userDto.getName());
        updateUser.setSurname(userDto.getSurname());
        updateUser.setBirthday(userDto.getBirthday());
        updateUser.setAddress(userDto.getAddress());

        mergeString(userDto, updateUser);
        return mapToDto(userRepository.save(updateUser));
    }

    @Override
    public void deleteUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
        userRepository.delete(userEntity);
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
    }

    public UserDto mapToDto(UserEntity userEntity){
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setAddress(userEntity.getAddress());
        userDto.setSurname(userEntity.getSurname());
        userDto.setName(userEntity.getName());
        userDto.setBirthday(userEntity.getBirthday());
        //userDto.setProjectEntities(userEntity.getProjectEntities());
        String[] projectInprogress = StringUtils.split(userEntity.getProjectInprogress(), "**");
        List<String> projectInprogresses = new ArrayList<>(Arrays.asList(projectInprogress));
        userDto.setProjectInprogress(projectInprogresses);

        String[] projectCompleted = StringUtils.split(userEntity.getProjectCompleted(), "**");
        List<String> listProjectCompleted = new ArrayList<>(Arrays.asList(projectCompleted));
        userDto.setProjectCompleted(listProjectCompleted);
        return userDto;
    }

    public UserEntity mapToEntity(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setAddress(userDto.getAddress());
        userEntity.setSurname(userDto.getSurname());
        userEntity.setName(userDto.getName());
        userEntity.setBirthday(userDto.getBirthday());
        //userEntity.setProjectEntities(userDto.getProjectEntities());
        mergeString(userDto, userEntity);

        return userEntity;
    }

    private void mergeString(UserDto userDto, UserEntity userEntity) {
        StringBuilder projectInprogress = new StringBuilder();

        for (String str : userDto.getProjectInprogress()){
            projectInprogress.append(str);
            projectInprogress.append("**");
        }
        userEntity.setProjectInprogress(projectInprogress.toString());

        StringBuilder projectCompleted = new StringBuilder();
        for (String str : userDto.getProjectCompleted()){
            projectCompleted.append(str);
            projectCompleted.append("**");
        }

        userEntity.setProjectCompleted(projectCompleted.toString());
    }
}
