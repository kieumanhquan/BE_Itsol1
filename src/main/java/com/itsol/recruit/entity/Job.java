package com.itsol.recruit.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Job")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Job {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
    @SequenceGenerator(name = "USERS_SEQ", sequenceName = "USERS_SEQ", allocationSize = 1, initialValue = 1)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "job_position_id")
    Long job_position_id;

    @Column(name = "number_experience")
    String number_experience;

    @Column(name = "working_form_id")
    Long working_form_id;

    @Column(name = "address_work")
    String address_work;

    @Column(name = "academic_level_id")
    Long academic_level_id;

    @Column(name = "rank_id")
    Long rank_id;

    @Column(name = "qty_person")
    Long qty_person;

    @Column(name = "start_recruitment_date")
    Date start_recruitment_date;
    @Column(name = "due_date")
    Date due_date;
    @Column(name = "skills")
    String skills;
    @Column(name = "description")
    String description;
    @Column(name = "interest")
    String interest;
    @Column(name = "job_requirement")
    String job_requirement;
    @Column(name = "salary_max")
    Long salary_max;
    @Column(name = "salary_min")
    Long salary_min;
    @Column(name = "contact_id")
    Long contact_id;
    @Column(name = "create_id")
    Long create_id;
    @Column(name = "create_date")
    Date create_date;
    @Column(name = "update_id")
    Long update_id;
    @Column(name = "update_date")
    Date update_date;
    @Column(name = "status_id")
    Long status_date;
    @Column(name = "views")
    String views;
    @Column(name = "is_delete")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    boolean isDelete;
}
