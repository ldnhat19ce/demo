package com.ldnhat.stdiomanagement.service.impl;

import com.ldnhat.stdiomanagement.dto.ProjectDto;
import com.ldnhat.stdiomanagement.entity.ProjectEntity;
import com.ldnhat.stdiomanagement.entity.UserEntity;
import com.ldnhat.stdiomanagement.mapper.ProjectMapper;
import com.ldnhat.stdiomanagement.repository.ProjectRepository;
import com.ldnhat.stdiomanagement.service.ProjectService;
import com.ldnhat.stdiomanagement.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository,
                              UserService userService, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.userService = userService;
        this.projectMapper = projectMapper;
    }

    @Override
    public List<ProjectDto> findAll() {
        return projectMapper.mapProjectEntitiesToProjectDto(projectRepository.findAll());
    }

    @Override
    public ProjectDto save(ProjectDto projectDto) {
        ProjectEntity projectEntity = projectMapper.mapProjectDtoToProjectEntity(projectDto);

        projectEntity.getUserEntities().forEach(it ->{
            UserEntity userEntity = userService.findById(it.getId());
            userEntity.getProjectEntities().add(projectEntity);
        });
        return projectMapper.mapProjectEntityToProjectDto(projectRepository.save(projectEntity));
    }

}
