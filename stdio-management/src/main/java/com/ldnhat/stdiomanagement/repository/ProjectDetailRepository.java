package com.ldnhat.stdiomanagement.repository;

import com.ldnhat.stdiomanagement.entity.ProjectDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDetailRepository extends JpaRepository<ProjectDetailEntity, Long> {
}
