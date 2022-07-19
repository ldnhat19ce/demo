package com.ldnhat.stdiomanagement.service.impl;

import com.ldnhat.stdiomanagement.dto.ProjectDto;
import com.ldnhat.stdiomanagement.dto.UserDto;
import com.ldnhat.stdiomanagement.entity.ProjectEntity;
import com.ldnhat.stdiomanagement.entity.UserEntity;
import com.ldnhat.stdiomanagement.repository.ProjectRepository;
import com.ldnhat.stdiomanagement.service.ProjectService;
import com.ldnhat.stdiomanagement.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private UserService userService;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, UserService userService) {
        this.projectRepository = projectRepository;
        this.userService = userService;
    }

    @Override
    public List<ProjectDto> findAll() {
        List<ProjectDto> projectDtos = new ArrayList<>();
        List<ProjectEntity> projectEntities = projectRepository.findAll();

        projectEntities.forEach(
                it -> projectDtos.add(mapToDto(it))
        );
        return projectDtos;
    }

    @Override
    public ProjectDto save(ProjectDto projectDto) {
        ProjectEntity projectEntity = mapToEntity(projectDto);

        projectEntity.getUserEntities().forEach(it ->{
            UserEntity userEntity = userService.findById(it.getId());
            projectEntity.addUser(userEntity);
        });

        return mapToDto(projectRepository.save(projectEntity));
    }

    public ProjectDto mapToDto(ProjectEntity projectEntity){
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(projectEntity.getId());
        projectDto.setProjectName(projectEntity.getProjectName());
        projectDto.setStatus(projectEntity.getStatus());
        projectDto.setUserEntities(projectEntity.getUserEntities());

        return projectDto;
    }

    public ProjectEntity mapToEntity(ProjectDto projectDto){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName(projectDto.getProjectName());
        projectEntity.setStatus(projectDto.getStatus());
        projectEntity.setUserEntities(projectDto.getUserEntities());

        return projectEntity;
    }

}
