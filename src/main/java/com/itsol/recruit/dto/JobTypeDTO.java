package com.itsol.recruit.dto;

import com.itsol.recruit.entity.Job;
import lombok.Data;

import java.util.List;
@Data
public class JobTypeDTO {
    List<Job> jobList;
    List<Integer> listPage;
}
