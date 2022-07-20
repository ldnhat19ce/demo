package com.ldnhat.stdiomanagement.mapper;

import com.ldnhat.stdiomanagement.dto.SkillDto;
import com.ldnhat.stdiomanagement.entity.SkillEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    SkillDto mapSkillEntityToSkillDto(SkillEntity skillEntity);
    SkillEntity mapSkillDtoToSkillEntity(SkillDto skillDto);
}
