package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.JobPosition;
import com.itsol.recruit.repository.JobPositionRepository;
import com.itsol.recruit.service.JobPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JobPositionServiceImpl implements JobPositionService {

    @Autowired
    JobPositionRepository jobPositionRepository;

    @Override
    public List<JobPosition> getAllJobPosition() {
        return jobPositionRepository.findAll();
    }

    @Override
    public JobPosition findById(Long id) {
        return jobPositionRepository.findById(id).get();
    }

}
