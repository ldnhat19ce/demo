package com.ldnhat.stdiomanagement.repository;

import com.ldnhat.stdiomanagement.common.Level;
import com.ldnhat.stdiomanagement.entity.SkillEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Long> {

    Page<SkillEntity> findAllByUserEntitiesIdOrderByLevelDesc(Long id, Pageable pagable);

    SkillEntity findByNameAndLevel(String name, Level level);
}
