package com.ldnhat.stdiomanagement.service;

import com.ldnhat.stdiomanagement.dto.ProjectDetailDto;

public interface ProjectDetailService {

    ProjectDetailDto save(ProjectDetailDto projectDetailDto, Long id);
    ProjectDetailDto edit(ProjectDetailDto projectDetailDto, Long projectDetailId, Long projectId);
    void deleteById(Long id, Long projectId);
}
