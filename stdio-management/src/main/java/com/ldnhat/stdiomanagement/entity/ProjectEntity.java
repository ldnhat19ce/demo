package com.ldnhat.stdiomanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void addUser(UserEntity userEntity){
        //this.userEntities.add(userEntity);
        userEntity.getProjectEntities().add(this);
    }
}
