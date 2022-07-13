package com.itsol.recruit.service;

import com.itsol.recruit.entity.JobPosition;

import java.util.List;

public interface JobPositionService {
    public List<JobPosition> getAllJobPosition();

    public JobPosition findById(Long id);

}
