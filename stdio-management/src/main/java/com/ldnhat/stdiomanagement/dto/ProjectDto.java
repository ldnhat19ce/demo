package com.ldnhat.stdiomanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ldnhat.stdiomanagement.common.Status;
import com.ldnhat.stdiomanagement.entity.UserEntity;
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

    @JsonProperty("userEntities")
    private List<UserEntity> userEntities = new ArrayList<>();

    @JsonProperty("status")
    private Status status;
}
