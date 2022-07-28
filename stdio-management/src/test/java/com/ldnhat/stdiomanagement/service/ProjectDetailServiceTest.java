package com.ldnhat.stdiomanagement.service;

import com.ldnhat.stdiomanagement.common.enums.ProjectDetailStatus;
import com.ldnhat.stdiomanagement.dto.ProjectDetailDto;
import com.ldnhat.stdiomanagement.entity.ProjectDetailEntity;
import com.ldnhat.stdiomanagement.entity.ProjectEntity;
import com.ldnhat.stdiomanagement.mapper.ProjectDetailMapper;
import com.ldnhat.stdiomanagement.repository.ProjectDetailRepository;
import com.ldnhat.stdiomanagement.repository.ProjectRepository;
import com.ldnhat.stdiomanagement.service.impl.ProjectDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectDetailServiceTest {

    @Mock
    private ProjectDetailRepository projectDetailRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectDetailMapper projectDetailMapper;

    @InjectMocks
    private ProjectDetailServiceImpl projectDetailService;

    @Test
    public void giveProjectDetail_whenSaveProjectDetail_thenReturnSavedProjectDetail(){
        Long id = 1L;

        ProjectEntity projectEntity = ProjectEntity.builder()
                .id(1L)
                .projectName("test1")
                .build();

        ProjectDetailEntity projectDetailEntity = ProjectDetailEntity.builder()
                .technical("java")
                .projectDetailStatus(ProjectDetailStatus.ACTIVE)
                .projectEntity(projectEntity)
                .build();

        ProjectDetailDto projectDetailDto = projectDetailMapper.
                INSTANCE.mapProjectDetailEntityToProjectDetailDto(projectDetailEntity);

        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(projectEntity));

        when(projectDetailRepository.save(any(ProjectDetailEntity.class))).thenReturn(projectDetailEntity);

        ProjectDetailDto actualProjectDetail = projectDetailService.save(projectDetailDto, id);
        assertThat(actualProjectDetail.getStatus()).isEqualTo(projectDetailDto.getStatus());
    }

    @Test
    public void giveProjectDetailAndProjectDetailIdAndProjectId_whenUpdateProjectDetail_thenReturnUpdatedProjectDetail(){
        Long projectId = 1L;
        Long projectDetailId = 1L;

        ProjectEntity projectEntity = ProjectEntity.builder()
                .id(1L)
                .build();

        ProjectDetailDto projectDetailDto = ProjectDetailDto.builder()
                .id(1L)
                .technical("java")
                .status("ACTIVE")
                .build();

        lenient().when(projectRepository.findById(anyLong())).thenReturn(Optional.of(projectEntity));

        lenient().when(projectDetailRepository.findById(anyLong())).thenReturn(Optional.of(
                projectDetailMapper.INSTANCE.mapProjectDetailDtoToProjectDetailEntity(projectDetailDto)
        ));

        lenient().when(projectDetailRepository.save(any(ProjectDetailEntity.class))).thenReturn(
          projectDetailMapper.INSTANCE.mapProjectDetailDtoToProjectDetailEntity(projectDetailDto)
        );

        projectDetailDto.setTechnical("test");

        ProjectDetailDto actualProjectDetail = projectDetailService.edit(projectDetailDto, projectDetailId, projectId);
        assertThat(actualProjectDetail.getTechnical()).isEqualTo(projectDetailDto.getTechnical());
    }

    @Test
    public void giveProjectIdAndProjectDetailId_whenDeleteProjectDetail_thenReturn200(){
        Long projectId = 1L;
        Long projectDetailId = 1L;

        ProjectEntity projectEntity = ProjectEntity.builder()
                .id(1L)
                .build();

        ProjectDetailEntity projectDetailEntity = ProjectDetailEntity.builder()
                .id(1L)
                .technical("java")
                .projectDetailStatus(ProjectDetailStatus.ACTIVE)
                .build();

        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(projectEntity));
        when(projectDetailRepository.findById(anyLong())).thenReturn(Optional.of(projectDetailEntity));

        willDoNothing().given(projectDetailRepository).delete(projectDetailEntity);
        projectDetailService.deleteById(projectId, projectDetailId);
        verify(projectDetailRepository).delete(projectDetailEntity);
    }
}
