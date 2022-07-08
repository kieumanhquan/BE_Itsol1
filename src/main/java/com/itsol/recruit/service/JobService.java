package com.itsol.recruit.service;

import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.entity.Job;

import java.util.List;

public interface JobService {

    public List<Job> getAllJob();

    public Job findById(Long id);

    public Job findJobByName(String name);

    //Trung` job <=> name + create_id_je
    public boolean isExistedJob(String name, Long createId);

    public boolean isDeleteJob(boolean isDelete);

    public Job insert(JobDTO jobDTO);

    public boolean delete(Long id);

}