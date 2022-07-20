package com.ldnhat.stdiomanagement.mapper;

import com.ldnhat.stdiomanagement.dto.ProjectDto;
import com.ldnhat.stdiomanagement.entity.ProjectEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @IterableMapping(qualifiedByName = "mapUserEntityInsideToUserDto")
    List<ProjectDto> mapProjectEntitiesToProjectDto(List<ProjectEntity> projectEntities);

    @Mapping(target = "userEntities", source = "userDtos")
    @Mapping(target = "status", source = "status")
    ProjectEntity mapProjectDtoToProjectEntity(ProjectDto projectDto);

    @Named("mapUserEntityInsideToUserDto")
    @Mapping(target = "userDtos", source = "userEntities")
    @Mapping(target = "status", source = "status")

    ProjectDto mapProjectEntityToProjectDto(ProjectEntity projectEntity);
}

