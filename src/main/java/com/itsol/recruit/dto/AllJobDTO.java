package com.itsol.recruit.dto;

import com.itsol.recruit.entity.Job;
import lombok.Data;

import java.util.List;
@Data
public class AllJobDTO {
    List<Job> newJob;
    List<Job> urgentJob;
    List<Job> highPJob;
}
