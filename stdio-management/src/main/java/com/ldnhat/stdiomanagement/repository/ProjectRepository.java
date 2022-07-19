package com.ldnhat.stdiomanagement.repository;

import com.ldnhat.stdiomanagement.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

}
