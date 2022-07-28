package com.ldnhat.stdiomanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldnhat.stdiomanagement.common.constant.Constant;
import com.ldnhat.stdiomanagement.common.provider.JwtTokenProvider;
import com.ldnhat.stdiomanagement.controller.api.SkillController;
import com.ldnhat.stdiomanagement.dto.SkillDto;
import com.ldnhat.stdiomanagement.entity.UserEntity;
import com.ldnhat.stdiomanagement.payload.Response;
import com.ldnhat.stdiomanagement.service.SkillService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = SkillController.class)
@SpringBootTest
@BootstrapWith(value = SpringBootTestContextBootstrapper.class)
@AutoConfigureMockMvc(addFilters = false)
public class SkillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private SkillService skillService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getListOfSkillByUserId_whenGetAllList_thenReturnListOfSkill() throws Exception {
        Long userId = 1L;
        List<SkillDto> listOfSkill = new ArrayList<>();
        listOfSkill.add(SkillDto.builder().id(1L).name("java").build());
        listOfSkill.add(SkillDto.builder().id(2L).name("php").build());
        listOfSkill.add(SkillDto.builder().id(3L).name("python").build());

        Response<SkillDto> skillDtoResponse = new Response<>();
        skillDtoResponse.setContent(listOfSkill);
        skillDtoResponse.setNumber(4);

        given(skillService.getSkillByUserId(0, 2,
                Constant.DEFAULT_SORT_DIRECTION, userId)).willReturn(skillDtoResponse);

        ResultActions response = mockMvc
                .perform(
                        get("/api/member/{userId}/skills?pageNo=0&pageSize=2&sortDir=asc",
                                userId)
        .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.number", is(4)));
    }

    @Test
    public void giveSkill_whenSaveSkill_thenReturnSavedSkill() throws Exception {
        SkillDto skillDto = SkillDto.builder()
                .name("java")
                .level("BASIC")
                .build();

        given(skillService.save(any(SkillDto.class))).willAnswer(
                (invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/member/skills")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(skillDto)));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(skillDto.getName())))
                .andExpect(jsonPath("$.level", is(skillDto.getLevel())));
    }

    @Test
    public void giveSkillAndUserId_whenSaveSkill_thenReturnSavedSkill() throws Exception {
        Long userId = 1L;
        SkillDto skillDto = SkillDto.builder()
                .name("java")
                .level("BASIC")
                .build();

        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .name("lenhat")
                .build();

        given(userService.findById(userId)).willReturn(userEntity);

        given(skillService.save(any(SkillDto.class), anyLong())).willAnswer(
                (invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/member/{userId}/skills", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(skillDto)));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(skillDto.getName())))
                .andExpect(jsonPath("$.level", is(skillDto.getLevel())));
    }
}
