package com.ldnhat.stdiomanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ldnhat.stdiomanagement.common.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@Setter
@Getter
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_name")
    private String projectName;

    @ManyToMany(mappedBy = "projectEntities", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"projectEntities"})
    private List<UserEntity> userEntities = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

}
