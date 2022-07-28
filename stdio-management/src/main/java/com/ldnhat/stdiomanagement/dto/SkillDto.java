package com.ldnhat.stdiomanagement.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkillDto implements Serializable {

    private Long id;

    private String name;

    private String level;

}
