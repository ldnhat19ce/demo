package com.ldnhat.stdiomanagement.service.impl;

import com.ldnhat.stdiomanagement.dto.SkillDto;
import com.ldnhat.stdiomanagement.entity.SkillEntity;
import com.ldnhat.stdiomanagement.entity.UserEntity;
import com.ldnhat.stdiomanagement.mapper.SkillMapper;
import com.ldnhat.stdiomanagement.repository.SkillRepository;
import com.ldnhat.stdiomanagement.response.SkillResponse;
import com.ldnhat.stdiomanagement.service.SkillService;
import com.ldnhat.stdiomanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;
    private final UserService userService;

    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository, SkillMapper skillMapper, UserService userService) {
        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
        this.userService = userService;
    }

    @Override
    public SkillResponse getSkillByUserId(int number, int pageSize, Long userId) {
        SkillResponse skillResponse = new SkillResponse();

        Pageable pageable = PageRequest.of(number, pageSize);
        Page<SkillEntity> pageSkill = skillRepository.findAllByUserEntitiesIdOrderByLevelDesc(userId, pageable);

        List<SkillEntity> listOfSkills = pageSkill.getContent();
        List<SkillDto> skillDtos = listOfSkills.stream().map(skillMapper::mapSkillEntityToSkillDto)
                .collect(Collectors.toList());

        skillResponse.setContent(skillDtos);
        skillResponse.setNumber(pageSkill.getNumber());
        skillResponse.setPageSize(pageSkill.getSize());
        skillResponse.setTotalElements(pageSkill.getTotalElements());
        skillResponse.setTotalPages(pageSkill.getTotalPages());
        return skillResponse;
    }

    @Override
    public SkillDto save(SkillDto skillDto, Long id) {
        SkillEntity skillEntity = skillMapper.mapSkillDtoToSkillEntity(skillDto);
        skillEntity = skillRepository.findByNameAndLevel(skillEntity.getName(), skillEntity.getLevel());

        UserEntity userEntity = userService.findById(id);
        skillEntity.getUserEntities().add(userEntity);
        userEntity.getSkillEntities().add(skillEntity);

        return skillMapper.mapSkillEntityToSkillDto(skillRepository.save(skillEntity));
    }
}
