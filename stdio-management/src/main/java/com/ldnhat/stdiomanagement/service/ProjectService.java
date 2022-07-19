package com.ldnhat.stdiomanagement.service;

import com.ldnhat.stdiomanagement.dto.ProjectDto;
import com.ldnhat.stdiomanagement.entity.ProjectEntity;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> findAll();

    ProjectDto save(ProjectDto projectDto);
}
