package com.ldnhat.stdiomanagement.mapper;

import com.ldnhat.stdiomanagement.dto.SkillDto;
import com.ldnhat.stdiomanagement.entity.SkillEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    @Mapping(target = "level", source = "level")
    SkillDto mapSkillEntityToSkillDto(SkillEntity skillEntity);

    @Mapping(target = "level", source = "level")
    SkillEntity mapSkillDtoToSkillEntity(SkillDto skillDto);
}
