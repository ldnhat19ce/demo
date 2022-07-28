package com.ldnhat.stdiomanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldnhat.stdiomanagement.common.provider.JwtTokenProvider;
import com.ldnhat.stdiomanagement.controller.api.ProjectDetailController;
import com.ldnhat.stdiomanagement.dto.ProjectDetailDto;
import com.ldnhat.stdiomanagement.dto.ProjectDto;
import com.ldnhat.stdiomanagement.entity.ProjectDetailEntity;
import com.ldnhat.stdiomanagement.repository.ProjectDetailRepository;
import com.ldnhat.stdiomanagement.service.ProjectDetailService;
import com.ldnhat.stdiomanagement.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ProjectDetailController.class)
@SpringBootTest
@BootstrapWith(value = SpringBootTestContextBootstrapper.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProjectDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;


    @MockBean
    private ProjectDetailService projectDetailService;


    @MockBean
    private ProjectDetailRepository projectDetailRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenProjectDetail_whenSaveProjectDetail_thenReturnSavedProjectDetail()
            throws Exception{
        Long projectId = 1L;
        ProjectDto projectDto = ProjectDto.builder().id(1L).build();
        ProjectDetailDto projectDetailDto = ProjectDetailDto.builder()
                                            .technical("java")
                                            .status("ACTIVE")
                                            .projectDto(projectDto)
                                            .build();

        given(projectDetailService.save(any(ProjectDetailDto.class), anyLong())).willAnswer(
                (invocationOnMock) -> invocationOnMock.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/project/{id}/create", projectId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(projectDetailDto)));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.technical", is(projectDetailDto.getTechnical())))
                .andExpect(jsonPath("$.status", is(projectDetailDto.getStatus())));
    }

    @Test
    public void giveProjectDetailAndProjectIdAndProjectDetailId_whenSaveProjectDetail_thenReturnUpdatedProjectDetail()
    throws Exception{
        Long projectId = 1L;
        Long projectDetailId = 1L;

        ProjectDetailDto updatedProjectDetail = ProjectDetailDto.builder()
                .status("END")
                .technical("mySQL")
                .build();

        given(projectDetailService.edit(any(ProjectDetailDto.class), anyLong(), anyLong())).willAnswer(
                (invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(put("/api/project/{id}/edit/{projectDetailId}",
                projectId, projectDetailId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedProjectDetail)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.technical", is(updatedProjectDetail.getTechnical())))
                .andExpect(jsonPath("$.status", is(updatedProjectDetail.getStatus())));
    }

    @Test
    public void giveProjectDetailIdAndProjectId_whenDeleteProjectDetail_thenReturn200()throws Exception{
        Long projectId = 1L;
        Long projectDetailId = 1L;

        ProjectDetailEntity projectDetailEntity = ProjectDetailEntity.builder()
                .id(projectDetailId)
                .build();

        willDoNothing().given(projectDetailRepository).delete(projectDetailEntity);

        ResultActions response = mockMvc.perform(delete("/api/project/{id}/delete/{projectDetailId}",
                projectId, projectDetailId));

        response.andExpect(status().isOk())
                .andDo(print());
    }

}
