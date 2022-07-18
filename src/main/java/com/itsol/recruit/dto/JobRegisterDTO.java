package com.itsol.recruit.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class JobRegisterDTO {
    long id;
    private String name;
    private String jobPosition;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateRegister;
    private String cv;
    private String status;
    private String reason;
}
