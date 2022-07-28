package com.ldnhat.stdiomanagement.dto;

import com.ldnhat.stdiomanagement.entity.SkillEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private Long id;

    private String surname;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String address;

    private String projectCompleted;

    private String activeProject;

    private List<SkillEntity> skillEntities = new ArrayList<>();

    private String username;

    private String password;

}
