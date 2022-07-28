package com.ldnhat.stdiomanagement.mapper;

import com.ldnhat.stdiomanagement.dto.ProjectDetailDto;
import com.ldnhat.stdiomanagement.entity.ProjectDetailEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProjectDetailMapper {

    ProjectDetailMapper INSTANCE = Mappers.getMapper(ProjectDetailMapper.class);

    @Mapping(target = "projectEntity", source = "projectDto")
    @Mapping(target = "projectDetailStatus", source = "status")
    ProjectDetailEntity mapProjectDetailDtoToProjectDetailEntity(ProjectDetailDto projectDetailDto);

    @Mapping(target = "projectDto", source = "projectEntity")
    @Mapping(target = "status", source = "projectDetailStatus")
    ProjectDetailDto mapProjectDetailEntityToProjectDetailDto(ProjectDetailEntity projectDetailEntity);
}
