package com.ldnhat.stdiomanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "address")
    private String address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_project",
            joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "project_id"))
    @JsonIgnoreProperties(value = {"userEntities"})
    private List<ProjectEntity> projectEntities = new ArrayList<>();

    @Column(name = "project_completed")
    private String projectCompleted;

    @Column(name = "project_inprogress")
    private String activeProject;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_skill",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    @JsonIgnoreProperties(value = {"userEntities"})
    private List<SkillEntity> skillEntities = new ArrayList<>();

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}
