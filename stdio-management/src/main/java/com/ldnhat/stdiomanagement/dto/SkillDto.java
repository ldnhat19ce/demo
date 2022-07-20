package com.ldnhat.stdiomanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ldnhat.stdiomanagement.common.Level;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("level")
    private Level level;

}
