package com.itsol.recruit.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class JobDTO {

    Long id;

    @NotEmpty(message = "Thiếu tên công việc")
    @Max(value = 150, message = "Tên công việc không quá 150 kí tự")
    String name;

    @NotEmpty(message = "Thiếu vị trí công việc")
    Long jobPositionId;

    @NotEmpty(message = "Thiếu năm kinh nghiệm")
    String numberExperience;

    @NotEmpty(message = "Thiếu hình thức làm việc")
    Long workingFormId;

    @NotEmpty(message = "Thiếu tên công việc")
    @Max(value = 300, message = "Tên công việc không quá 300 kí tự")
    String addressWork;

    Long academicLevelId;

    Long rankId;

    Long qtyPerson;

    Date startRecruitmentDate;

    Date dueDate;

    String skills;

    String description;

    String interest;

    String jobRequirement;

    Long salaryMax;

    Long salaryMin;

    Long contactId;

    Long createId;

    Date createDate;

    Long updateId;

    Date updateDate;

    Long statusId;

    Long views;

    boolean isDelete;

}
