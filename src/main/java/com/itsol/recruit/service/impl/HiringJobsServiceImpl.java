package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.Job;
import com.itsol.recruit.repository.HiringJobRepository;
import com.itsol.recruit.service.HiringJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HiringJobsServiceImpl implements HiringJobsService {
    @Autowired
    HiringJobRepository hiringJobRepository;

    public HiringJobsServiceImpl(HiringJobRepository hiringJobRepository) {
        this.hiringJobRepository = hiringJobRepository;
    }

    @Override
    public List<Job> getAllJob() {
        return hiringJobRepository.findAll();
    }
}
