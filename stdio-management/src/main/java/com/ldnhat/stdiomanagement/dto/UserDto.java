package com.ldnhat.stdiomanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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

    @JsonProperty("projectCompleted")
    private String projectCompleted;

    @JsonProperty("activeProject")
    private String activeProject;


}
