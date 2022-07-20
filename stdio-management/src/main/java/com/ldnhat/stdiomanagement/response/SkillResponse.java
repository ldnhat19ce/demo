package com.ldnhat.stdiomanagement.response;

import com.ldnhat.stdiomanagement.dto.SkillDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SkillResponse {

    private List<SkillDto> content;
    private int number;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
}
