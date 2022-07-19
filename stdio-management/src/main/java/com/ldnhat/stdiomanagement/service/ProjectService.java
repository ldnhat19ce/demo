package com.ldnhat.stdiomanagement.service;

import com.ldnhat.stdiomanagement.dto.ProjectDto;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> findAll();

    ProjectDto save(ProjectDto projectDto);
}
