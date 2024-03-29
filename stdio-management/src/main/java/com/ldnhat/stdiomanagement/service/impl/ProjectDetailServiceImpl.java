package com.ldnhat.stdiomanagement.service.impl;

import com.ldnhat.stdiomanagement.dto.ProjectDetailDto;
import com.ldnhat.stdiomanagement.entity.ProjectDetailEntity;
import com.ldnhat.stdiomanagement.entity.ProjectEntity;
import com.ldnhat.stdiomanagement.exception.ResourceNotFoundException;
import com.ldnhat.stdiomanagement.mapper.ProjectDetailMapper;
import com.ldnhat.stdiomanagement.repository.ProjectDetailRepository;
import com.ldnhat.stdiomanagement.repository.ProjectRepository;
import com.ldnhat.stdiomanagement.service.ProjectDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProjectDetailServiceImpl implements ProjectDetailService {

    private ProjectDetailRepository projectDetailRepository;
    private ProjectDetailMapper projectDetailMapper;
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectDetailServiceImpl(ProjectDetailRepository projectDetailRepository, ProjectDetailMapper projectDetailMapper,
                                    ProjectRepository projectRepository) {
        this.projectDetailRepository = projectDetailRepository;
        this.projectDetailMapper = projectDetailMapper;
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectDetailDto save(ProjectDetailDto projectDetailDto, Long id) {
        ProjectDetailEntity projectDetailEntity = projectDetailMapper.INSTANCE
                .mapProjectDetailDtoToProjectDetailEntity(projectDetailDto);

        ProjectEntity projectEntity = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("project", "id", id));

        projectDetailEntity.setProjectEntity(projectEntity);
        return projectDetailMapper.INSTANCE.mapProjectDetailEntityToProjectDetailDto(
                        projectDetailRepository.save(projectDetailEntity));
    }

    @Override
    public ProjectDetailDto edit(ProjectDetailDto projectDetailDto, Long projectDetailId,
                                 Long projectId) {
        ProjectDetailEntity projectDetailEntity = projectDetailMapper.INSTANCE.
                mapProjectDetailDtoToProjectDetailEntity(projectDetailDto);
        projectRepository.findById(projectId).orElseThrow(() ->
                new ResourceNotFoundException("project", "id", projectId));
        ProjectDetailEntity updateProjectDetail = projectDetailRepository.findById(projectDetailId)
                .orElseThrow(() -> new ResourceNotFoundException("project detail", "id", projectDetailId));

        updateProjectDetail.setProjectDetailStatus(projectDetailEntity.getProjectDetailStatus());
        updateProjectDetail.setTimeStart(projectDetailEntity.getTimeStart());
        updateProjectDetail.setTimeEnd(projectDetailEntity.getTimeEnd());
        updateProjectDetail.setTechnical(projectDetailEntity.getTechnical());

        return projectDetailMapper.INSTANCE.mapProjectDetailEntityToProjectDetailDto(
                updateProjectDetail
        );
    }

    @Override
    public void deleteById(Long id, Long projectId) {

        projectRepository.findById(projectId).orElseThrow(() ->
                new ResourceNotFoundException("project ", "id", projectId));

        ProjectDetailEntity projectDetailEntity = projectDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("project detail", "id", id));
        projectDetailRepository.delete(projectDetailEntity);
    }
}
