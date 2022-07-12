package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.Job;
import com.itsol.recruit.repository.HiringJobRepository;
import com.itsol.recruit.repository.repoimpl.JobRepositoryImpl;
import com.itsol.recruit.service.HiringJobsService;
import com.itsol.recruit.web.vm.SearchJobVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HiringJobsServiceImpl implements HiringJobsService {
    @Autowired
    private final  HiringJobRepository hiringJobRepository;
    private final JobRepositoryImpl jobRepository;
    public HiringJobsServiceImpl(HiringJobRepository hiringJobRepository, JobRepositoryImpl jobRepository) {
        this.hiringJobRepository = hiringJobRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> getAllJob() {
        return hiringJobRepository.findAll();
    }

    @Override
    public List<Job> searchJob(SearchJobVM searchVM) {
        return jobRepository.searchJob(searchVM);
    }
}
