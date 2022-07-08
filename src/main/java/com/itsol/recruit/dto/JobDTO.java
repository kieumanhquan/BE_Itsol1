package com.itsol.recruit.dto;

import com.itsol.recruit.entity.*;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class JobDTO {

    @NotEmpty(message = "Thiếu tên công việc")
    @Max(value = 150, message = "Tên công việc không quá 150 kí tự")
    String name;

    Long jobPositionId;

    String numberExperience;

    Long workingFormId;

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

    long views;

}
