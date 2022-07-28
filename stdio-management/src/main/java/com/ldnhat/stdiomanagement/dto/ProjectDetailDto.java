package com.ldnhat.stdiomanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDetailDto {

    private Long id;
    private String status;
    private Date timeStart;
    private Date timeEnd;
    private String technical;

    @JsonProperty("project")
    private ProjectDto projectDto;
}
