package com.ldnhat.stdiomanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ldnhat.stdiomanagement.entity.ProjectEntity;
import com.ldnhat.stdiomanagement.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Date birthday;

    @JsonProperty("address")
    private String address;

//    @JsonProperty("projectEntities")
//    private Set<ProjectEntity> projectEntities;

    @JsonProperty("projectCompleted")
    private List<String> projectCompleted;

    @JsonProperty("projectInprogress")
    private List<String> projectInprogress;


}
