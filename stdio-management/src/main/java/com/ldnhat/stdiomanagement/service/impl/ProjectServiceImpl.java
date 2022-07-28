package com.ldnhat.stdiomanagement.service.impl;

import com.ldnhat.stdiomanagement.dto.ProjectDto;
import com.ldnhat.stdiomanagement.entity.ProjectEntity;
import com.ldnhat.stdiomanagement.entity.UserEntity;
import com.ldnhat.stdiomanagement.exception.ResourceNotFoundException;
import com.ldnhat.stdiomanagement.mapper.ProjectMapper;
import com.ldnhat.stdiomanagement.mapper.UserMapper;
import com.ldnhat.stdiomanagement.repository.ProjectRepository;
import com.ldnhat.stdiomanagement.repository.UserRepository;
import com.ldnhat.stdiomanagement.service.ProjectService;
import com.ldnhat.stdiomanagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository,
                              UserRepository userRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public List<ProjectDto> findAll() {
        return projectMapper.mapProjectEntitiesToProjectDto(projectRepository.findAll());
    }

    @Override
    public ProjectDto save(ProjectDto projectDto) {
        ProjectEntity projectEntity = projectMapper.INSTANCE.mapProjectDtoToProjectEntity(projectDto);
        List<ProjectEntity> projectEntities = new ArrayList<>(Collections.singleton(projectEntity));
        projectEntity.getUserEntities().forEach(it ->{
            UserEntity userEntity = userRepository.findById(it.getId()).orElseThrow(
                    () -> new ResourceNotFoundException("user", "id", it.getId())
            );
            userEntity.setProjectEntities(projectEntities);
        });
        return projectMapper.mapProjectEntityToProjectDto(projectRepository.save(projectEntity));
    }

}
