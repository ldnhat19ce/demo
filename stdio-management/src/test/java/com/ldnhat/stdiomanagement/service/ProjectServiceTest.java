package com.ldnhat.stdiomanagement.service;

import com.ldnhat.stdiomanagement.dto.ProjectDto;
import com.ldnhat.stdiomanagement.dto.UserDto;
import com.ldnhat.stdiomanagement.entity.ProjectEntity;
import com.ldnhat.stdiomanagement.entity.UserEntity;
import com.ldnhat.stdiomanagement.mapper.ProjectMapper;
import com.ldnhat.stdiomanagement.repository.ProjectRepository;
import com.ldnhat.stdiomanagement.repository.UserRepository;
import com.ldnhat.stdiomanagement.service.impl.ProjectServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {


    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMapper projectMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;


    @Test
    public void giveGetAll_thenReturnGetAllList(){
        List<ProjectDto> listOfProject = new ArrayList<>();
        listOfProject.add(ProjectDto.builder().id(1L).projectName("test1").build());
        listOfProject.add(ProjectDto.builder().id(2L).projectName("test2").build());
        listOfProject.add(ProjectDto.builder().id(3L).projectName("test3").build());

        given(projectMapper.mapProjectEntitiesToProjectDto(projectRepository.findAll()))
        .willReturn(listOfProject);

        List<ProjectDto> actualProject = projectService.findAll();

        assertThat(actualProject.size()).isEqualTo(listOfProject.size());
        assertThat(actualProject.size()).isEqualTo(3);
    }

    @Test
    public void giveProject_whenSaveProject_thenReturnSavedProject(){

        List<UserDto> listOfUser = new ArrayList<>();
        listOfUser.add(UserDto.builder().id(1L).build());
        listOfUser.add(UserDto.builder().id(2L).build());
        listOfUser.add(UserDto.builder().id(3L).build());

        ProjectDto projectDto = ProjectDto.builder()
                .projectName("test1")
                .status("IN_PROGRESS")
                .userDtos(listOfUser)
                .build();

        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .name("le")
                .build();

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));

        lenient().when(projectMapper.mapProjectEntityToProjectDto(projectRepository.save(any(ProjectEntity.class))))
        .thenReturn(projectDto);

        ProjectDto actualProject = projectService.save(projectDto);
        assertThat(actualProject.getStatus()).isEqualTo(projectDto.getStatus());
    }
}
