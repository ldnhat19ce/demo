package com.ldnhat.stdiomanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldnhat.stdiomanagement.common.provider.JwtTokenProvider;
import com.ldnhat.stdiomanagement.controller.api.ProjectController;
import com.ldnhat.stdiomanagement.dto.ProjectDto;
import com.ldnhat.stdiomanagement.dto.UserDto;
import com.ldnhat.stdiomanagement.service.ProjectService;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ProjectController.class)
@SpringBootTest
@BootstrapWith(value = SpringBootTestContextBootstrapper.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private ProjectService projectService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenListOfProject_whenGetAllProject_thenReturnListProject() throws Exception {
        List<ProjectDto> listOfProject = new ArrayList<>();
        listOfProject.add(ProjectDto.builder().id(1L).projectName("test1").build());
        listOfProject.add(ProjectDto.builder().id(2L).projectName("test2").build());
        listOfProject.add(ProjectDto.builder().id(3L).projectName("test3").build());

        given(projectService.findAll()).willReturn(listOfProject);

        ResultActions response = mockMvc.perform(get("/api/project/list"));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(listOfProject.size())));
    }

    @Test
    public void giveProject_whenSaveProject_thenReturnSavedProject() throws Exception {
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(UserDto.builder().id(1L).build());
        userDtos.add(UserDto.builder().id(2L).build());
        userDtos.add(UserDto.builder().id(3L).build());

        ProjectDto projectDto = ProjectDto.builder()
                .projectName("test1")
                .status("IN_PROGRESS")
                .userDtos(userDtos)
                .build();

        given(projectService.save(any(ProjectDto.class))).willAnswer(
                (invocationOnMock) -> invocationOnMock.getArgument(0)
        );

        ResultActions response = mockMvc.perform(post("/api/project/create")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(projectDto)));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status", is(projectDto.getStatus())))
                .andExpect(jsonPath("$.projectName", is(projectDto.getProjectName())));

    }
}
