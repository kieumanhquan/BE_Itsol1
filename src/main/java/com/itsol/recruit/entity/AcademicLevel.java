package com.itsol.recruit.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity(name = "academic_level")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AcademicLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_ACADEMIC_LEVEL_ID")
    @SequenceGenerator(name = "GEN_ACADEMIC_LEVEL_ID", sequenceName = "SEQ_ACADEMIC_LEVEL", allocationSize = 1)

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