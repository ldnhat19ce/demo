package com.ldnhat.stdiomanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldnhat.stdiomanagement.common.custom.CustomUserDetails;
import com.ldnhat.stdiomanagement.common.provider.JwtTokenProvider;
import com.ldnhat.stdiomanagement.controller.api.UserController;
import com.ldnhat.stdiomanagement.dto.UserDto;
import com.ldnhat.stdiomanagement.entity.UserEntity;
import com.ldnhat.stdiomanagement.mapper.UserMapper;
import com.ldnhat.stdiomanagement.repository.UserRepository;
import com.ldnhat.stdiomanagement.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserController.class, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtTokenProvider.class)
})
@SpringBootTest
@BootstrapWith(value = SpringBootTestContextBootstrapper.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;


    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ObjectMapper objectMapper;

    private static CustomUserDetails customUserDetails;
    private static String jwtToken;

    @BeforeEach
    public void setup(){
        UserEntity userEntity = UserEntity.builder().id(1L).username("lenhat").password("123456").build();
        customUserDetails = new CustomUserDetails(userEntity);
        jwtToken = jwtTokenProvider.generateToken(customUserDetails);
    }

    @Test
    public void givenListOfUsers_whenGetAllUsers_thenReturnUsersList() throws Exception {

        List<UserDto> listOfUsersDto = new ArrayList<>();
        listOfUsersDto.add(UserDto.builder().id(1L).address("dn").name("test1").build());
        listOfUsersDto.add(UserDto.builder().id(2L).address("kt").name("test2").build());
        listOfUsersDto.add(UserDto.builder().id(3L).address("kh").name("test3").build());


        given(userService.findAll()).willReturn(listOfUsersDto);
        jwtTokenProvider = mock(JwtTokenProvider.class);
        when(jwtTokenProvider.generateToken(customUserDetails)).thenReturn("anyToken");
        given(userService.loadUsernameById(anyLong())).willReturn(customUserDetails);

        ResultActions response = mockMvc.perform(get("/api/member/list")
        .header(HttpHeaders.AUTHORIZATION, "Bearer "+jwtToken));

        response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.size()", is(listOfUsersDto.size())));

    }

    @Test
    public void givenUser_whenSaveUser_thenReturnSavedUser() throws Exception {
        UserDto userDto = UserDto.builder()
                .address("kt")
                .surname("le")
                .name("nhat")
                .password("$2a$10$fWACj0.j2qogpm94EEAW8u4cltIcYu69pBqU7qawRJZKnVmdm.g9S")
                .build();

        given(userService.save(any(UserDto.class))).willAnswer(
                (invocationOnMock) -> invocationOnMock.getArgument(0));
        jwtTokenProvider = mock(JwtTokenProvider.class);
        when(jwtTokenProvider.generateToken(customUserDetails)).thenReturn("anyToken");
        given(userService.loadUsernameById(anyLong())).willReturn(customUserDetails);

        ResultActions response = mockMvc.perform(post("/api/member/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(userDto))
        .header(HttpHeaders.AUTHORIZATION, "Bearer "+jwtToken));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.address", is(userDto.getAddress())))
                .andExpect(jsonPath("$.surname", is(userDto.getSurname())))
                .andExpect(jsonPath("$.name", is(userDto.getName())));
    }

    @Test
    public void givenUserAndId_whenUpdateUser_thenReturnUpdatedUser() throws Exception {
        Long userId = 1L;

        UserDto savedUserDto = UserDto.builder()
                .username("le duc")
                .name("nhat")
                .address("kt")
                .build();

        UserDto updatedUserDto = UserDto.builder()
                .username("test1")
                .name("test2")
                .address("test3")
                .build();

        UserEntity userEntity = userMapper.INSTANCE.mapUserDtoToUserEntity(savedUserDto);

        given(userRepository.findById(userId)).willReturn(Optional.of(userEntity));
        given(userService.edit(any(UserDto.class), anyLong()))
            .willAnswer((invocation) -> invocation.getArgument(0));
        jwtTokenProvider = mock(JwtTokenProvider.class);
        when(jwtTokenProvider.generateToken(customUserDetails)).thenReturn("anyToken");
        given(userService.loadUsernameById(anyLong())).willReturn(customUserDetails);

        ResultActions response = mockMvc.perform(put("/api/member/update/{id}", userId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer "+jwtToken)
                                .content(objectMapper.writeValueAsString(updatedUserDto)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address", is(updatedUserDto.getAddress())))
                .andExpect(jsonPath("$.surname", is(updatedUserDto.getSurname())))
                .andExpect(jsonPath("$.name", is(updatedUserDto.getName())));
    }

    @Test
    public void givenUserId_whenDeleteUser_thenReturn200() throws Exception {
        Long userId = 1L;

        UserDto userDto = UserDto.builder().id(1L).build();

        UserEntity userEntity = userMapper.INSTANCE.mapUserDtoToUserEntity(userDto);
        given(userRepository.findById(userId))
                .willReturn(Optional.of(userEntity));
        willDoNothing().given(userRepository).delete(userEntity);
        jwtTokenProvider = mock(JwtTokenProvider.class);
        when(jwtTokenProvider.generateToken(customUserDetails)).thenReturn("anyToken");
        given(userService.loadUsernameById(anyLong())).willReturn(customUserDetails);

        ResultActions response = mockMvc.perform(delete("/api/member/delete/{id}", userId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+jwtToken));

        response.andExpect(status().isOk())
                .andDo(print());
    }
}
