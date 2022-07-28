package com.ldnhat.stdiomanagement.mapper;

import com.ldnhat.stdiomanagement.dto.SkillDto;
import com.ldnhat.stdiomanagement.entity.SkillEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    @Mapping(target = "level", source = "level")
    SkillDto mapSkillEntityToSkillDto(SkillEntity skillEntity);

    @Named("mapSkillDtoToSkillEntity")
    @Mapping(target = "level", source = "level")
    SkillEntity mapSkillDtoToSkillEntity(SkillDto skillDto);

    @IterableMapping(qualifiedByName = "mapSkillDtoToSkillEntity")
    List<SkillEntity> mapSkillDtosToSkillEntities(List<SkillDto> skillDtos);
}
