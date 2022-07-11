package com.itsol.recruit.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;

@Entity (name = "job")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Job {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JOBS_SEQ")
    @SequenceGenerator(name = "JOBS_SEQ", sequenceName = "JOBS_SEQ", allocationSize = 1, initialValue = 1)
    Long id;

    @Column(name = "NAME", nullable = false)
    String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JOB_POSITION_ID", nullable = false)
    JobPosition jobPosition;

    @Column(name = "NUMBER_EXPERIENCE", nullable = false)
    String numberExperience;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "WORKING_FORM_ID", nullable = false)
    WorkingForm workingForm;


    @Column(name = "ADDRESS_WORK", nullable = false)
    String addressWork;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACADEMIC_LEVEL_ID", nullable = false)
    AcademicLevel academicLevel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RANK_ID", nullable = false)
    Rank rank;

    @Column(name = "QTY_PERSON", nullable = false)
    Long qtyPerson;

    @Column(name = "START_RECRUITMENT_DATE", nullable = false)
    Date startRecruitmentDate;

    @Column(name = "DUE_DATE", nullable = false)
    Date dueDate;

    @Column(name = "SKILLS", nullable = false)
    String skills;

    @Column(name = "DESCRIPTION", nullable = false)
    String description;

    @Column(name = "INTEREST", nullable = false)
    String interest;

    @Column(name = "JOB_REQUIREMENT", nullable = false)
    String jobRequirement;

    @Column(name = "SALARY_MAX", nullable = false)
    Long salaryMax;
    @Column(name = "SALARY_MIN", nullable = false)
    Long salaryMin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CONTACT_ID", nullable = false)
    User contact;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CREATE_ID", nullable = false)
    User create;

    @Column(name = "CREATE_DATE", nullable = false)
    Date createDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UPDATE_ID", nullable = false)
    User update;

    @Column(name = "UPDATE_DATE", nullable = false)
    Date updateDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STATUS_ID", nullable = false)
    StatusJob status;

    @Column(name = "VIEWS")
    long views;

    @Column(name = "IS_DELETE", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    boolean isDelete;
}
