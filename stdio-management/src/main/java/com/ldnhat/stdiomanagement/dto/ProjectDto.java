package com.ldnhat.stdiomanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDto {

    private Long id;

    private String projectName;

    @JsonProperty("users")
    @JsonIgnoreProperties(value = {"projectEntities", "userEntities"})
    private List<UserDto> userDtos = new ArrayList<>();

    private String status;
}
