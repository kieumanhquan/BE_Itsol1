package com.itsol.recruit.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class FileRecruitDTO {
    private String name;
    private String jobPosition;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateRegister;
    private String cv;
    private String status;
    private String reason;
}
