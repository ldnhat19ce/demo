package com.ldnhat.stdiomanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ldnhat.stdiomanagement.entity.Status;
import com.ldnhat.stdiomanagement.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
