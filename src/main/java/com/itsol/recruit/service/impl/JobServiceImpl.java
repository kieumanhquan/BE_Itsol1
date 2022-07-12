package com.itsol.recruit.service.impl;

import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.repository.JobRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.JobService;
import com.itsol.recruit.service.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JobServiceImpl implements JobService {

/*    public final JobRepository jobRepository;

    public final JobMapper jobMapper;

    public JobServiceImpl(JobRepository jobRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }*/

    @Autowired
    JobRepository jobRepository;

    @Autowired
    JobMapper jobMapper;

    @Override
    public List<Job> getAllJob() {
        return jobRepository.findAll();
    }


    @Override
    public Job findById(Long id) {
        return jobRepository.findById(id).get();
    }

    @Override
    public Job findJobByName(String name) {
        return jobRepository.findJobByName(name);
    }

    @Override
    public boolean isExistedJob(String name, Long createId) {
        return false;
    }

    @Override
    public boolean isDeleteJob(boolean isDelete) {
        return false;
    }

    @Override
    public Job insert(JobDTO jobDTO) {

        Job job = jobMapper.toEntity(jobDTO);
        return jobRepository.save(job);
    }


    @Override
    public boolean delete(Long id) {
        Job job = this.findById(id);
        if(job == null) return false;
        job.setDelete(true);
        return true;
    }
}
