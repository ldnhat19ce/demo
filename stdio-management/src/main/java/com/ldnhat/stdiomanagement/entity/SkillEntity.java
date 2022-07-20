package com.ldnhat.stdiomanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ldnhat.stdiomanagement.common.enums.Level;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "skills")
@Getter
@Setter
public class SkillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private Level level;

    @ManyToMany(mappedBy = "skillEntities", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"skillEntities"})
    private List<UserEntity> userEntities = new ArrayList<>();
}
