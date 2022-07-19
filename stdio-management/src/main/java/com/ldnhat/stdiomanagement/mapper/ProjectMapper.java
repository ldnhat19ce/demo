package com.ldnhat.stdiomanagement.mapper;

import com.ldnhat.stdiomanagement.dto.ProjectDto;
import com.ldnhat.stdiomanagement.entity.ProjectEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    List<ProjectDto> mapProjectEntitiesToProjectDto(List<ProjectEntity> projectEntities);
    ProjectEntity mapProjectDtoToProjectEntity(ProjectDto projectDto);
    ProjectDto mapProjectEntityToProjectDto(ProjectEntity projectEntity);
}

