package com.ldnhat.stdiomanagement.repository;

import com.ldnhat.stdiomanagement.common.enums.Level;
import com.ldnhat.stdiomanagement.entity.SkillEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Long> {

    Page<SkillEntity> findAllByUserEntitiesId(Long id, Pageable pagable);

    Optional<SkillEntity> findByNameAndLevel(String name, Level level);
}
