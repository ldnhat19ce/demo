package com.ldnhat.stdiomanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ldnhat.stdiomanagement.entity.SkillEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("name")
    private String name;

    @JsonProperty("birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @JsonProperty("address")
    private String address;

    @JsonProperty("projectCompleted")
    private String projectCompleted;

    @JsonProperty("activeProject")
    private String activeProject;

    @JsonProperty("skillEntities")
    private List<SkillEntity> skillEntities = new ArrayList<>();

    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

}
