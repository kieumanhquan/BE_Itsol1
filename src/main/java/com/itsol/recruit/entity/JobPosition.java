package com.itsol.recruit.entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;


@Entity (name = "job_position")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class JobPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_JOB_POSITION_ID")
    @SequenceGenerator(name = "GEN_JOB_POSITION_ID", sequenceName = "SEQ_JOB_POSITION", allocationSize = 1)

    @Column(name = "ID", nullable = false)
    Long id;

    @Column(name = "CODE", nullable = false)
    String code;

    @Column(name = "DESCRIPTION", nullable = false)
    String description;

    @Column(name = "IS_DELETE", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    boolean isDelete;

}
