package com.ldnhat.stdiomanagement.repository;

import com.ldnhat.stdiomanagement.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    ProjectEntity findByProjectName(String projectName);
}
