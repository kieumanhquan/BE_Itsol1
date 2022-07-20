package com.itsol.recruit.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "job_register")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobRegister{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_JOR_ID")
    @SequenceGenerator(name = "GEN_JOR_ID", sequenceName = "JOBREG_SEQ", allocationSize = 1,initialValue = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date_register")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateRegister;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "date_interview")
    private Date dateInterview;

    @Column(name = "time_interview")
    private Integer timeInterview;

    @Column(name = "method_interview")
    private String methodInterview;

    @Column(name = "address_interview")
    private String addressInterview;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusJobRegister statusJobRegister;

    @OneToOne
    @JoinColumn(name = "cv_file")
    private FileDB fileDB;

    @Column(name = "media_type")
    private String mediaType;

    @Column(name = "reason")
    private String reason;

    @Column(name = "is_delete")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isDelete;

}