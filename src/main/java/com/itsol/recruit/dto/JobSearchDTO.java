package com.itsol.recruit.dto;

import com.itsol.recruit.entity.Job;
import lombok.Data;

import java.util.List;
@Data
public class JobSearchDTO {
    List<Job> jobList;
    List<Integer> pageNumberList;
}
