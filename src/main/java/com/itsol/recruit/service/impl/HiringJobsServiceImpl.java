package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.Job;
import com.itsol.recruit.repository.HiringJobRepository;
import com.itsol.recruit.repository.JobRepository;
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
    private final JobRepositoryImpl jobRepositoryImpl;
    private final JobRepository jobRepository;
    public HiringJobsServiceImpl(HiringJobRepository hiringJobRepository, JobRepository jobRepository, JobRepositoryImpl jobRepositoryImpl) {
        this.hiringJobRepository = hiringJobRepository;
        this.jobRepositoryImpl = jobRepositoryImpl;
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> getAllJob() {
        return hiringJobRepository.findAll();
    }

    @Override
    public List<Job> searchJob(SearchJobVM searchVM) {
        return jobRepositoryImpl.searchJob(searchVM);
    }



}
