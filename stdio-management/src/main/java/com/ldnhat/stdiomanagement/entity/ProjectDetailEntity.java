package com.ldnhat.stdiomanagement.entity;

import com.ldnhat.stdiomanagement.common.enums.ProjectDetailStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "project_detail")
@Getter
@Setter
public class ProjectDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProjectDetailStatus projectDetailStatus;

    @Column(name = "time_start")
    private Date timeStart;

    @Column(name = "time_end")
    private Date timeEnd;

    @Column(name = "technical")
    private String technical;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private ProjectEntity projectEntity;
}
