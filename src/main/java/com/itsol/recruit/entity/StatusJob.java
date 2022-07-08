package com.itsol.recruit.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity(name = "status_job")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class StatusJob {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_STATUS_JOB_ID")
    @SequenceGenerator(name = "GEN_STATUS_JOB_ID", sequenceName = "SEQ_STATUS_JOB", allocationSize = 1)

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

