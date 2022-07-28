package com.ldnhat.stdiomanagement.service;

import com.ldnhat.stdiomanagement.common.constant.Constant;
import com.ldnhat.stdiomanagement.dto.SkillDto;
import com.ldnhat.stdiomanagement.entity.SkillEntity;
import com.ldnhat.stdiomanagement.mapper.SkillMapper;
import com.ldnhat.stdiomanagement.payload.Response;
import com.ldnhat.stdiomanagement.repository.SkillRepository;
import com.ldnhat.stdiomanagement.service.impl.SkillServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class SkillServiceTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillServiceImpl skillService;

    @Mock
    private SkillMapper skillMapper;

    @Test
    public void givenGetAllByUserId_thenReturnList(){

        Long userId = 1L;

        List<SkillDto> listOfSkill = new ArrayList<>();
        listOfSkill.add(SkillDto.builder().id(1L).name("java").build());
        listOfSkill.add(SkillDto.builder().id(2L).name("php").build());
        listOfSkill.add(SkillDto.builder().id(3L).name("python").build());

        Response<SkillDto> response = new Response<>();
        response.setContent(listOfSkill);

        Page<SkillEntity> skillEntities = new
                PageImpl<>(skillMapper.INSTANCE.mapSkillDtosToSkillEntities(listOfSkill));

       Pageable pageable = PageRequest.of(1, 1,
               Sort.by(Constant.DEFAULT_SORT_BY_OF_SKILL).ascending());
        given(skillRepository.findAllByUserEntitiesId(userId, pageable))
        .willReturn(skillEntities);

        Response<SkillDto> skillDtoResponse = skillService.getSkillByUserId(1, 1,
                "asc", userId);

        assertThat(skillDtoResponse.getContent().size()).isEqualTo(listOfSkill.size());
    }

    @Test
    public void giveSkill_whenSaveSkill_thenReturnSavedSkill(){
        SkillDto skillDto = SkillDto.builder()
                .name("java")
                .level("BASIC")
                .build();
        SkillEntity skillEntity = SkillMapper.INSTANCE.mapSkillDtoToSkillEntity(skillDto);

        given(skillMapper.mapSkillEntityToSkillDto(skillRepository.save(skillEntity)))
                .willReturn(skillDto);

        SkillDto actualSkill = skillService.save(skillDto);

        assertThat(actualSkill.getName()).isEqualTo(skillDto.getName());
    }

}
