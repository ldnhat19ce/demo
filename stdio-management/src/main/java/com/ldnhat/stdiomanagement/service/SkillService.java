package com.ldnhat.stdiomanagement.service;

import com.ldnhat.stdiomanagement.dto.SkillDto;
import com.ldnhat.stdiomanagement.response.SkillResponse;

public interface SkillService {
    SkillResponse getSkillByUserId(int pageNo, int pageSize, Long userId);
    SkillDto save(SkillDto skillDto, Long id);
}
