package com.ldnhat.stdiomanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProjectDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("projectName")
    private String projectName;

    @JsonProperty("users")
    @JsonIgnoreProperties(value = {"projectEntities", "userEntities"})
    private List<UserDto> userDtos = new ArrayList<>();

    @JsonProperty("status")
    private String status;
}
