package com.itsol.recruit.service;

import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.entity.Job;
import org.springframework.data.domain.Page;

public interface JobService {

    public Page<Job> getAllJob(int page, int pageSize, String sort, boolean type);

    public Job findById(Long id);

    public Job findJobByName(String name);

    //Trung` job <=> name + create_id_je
    public boolean isExistedJob(String name, Long createId);

    public boolean isDeleteJob(boolean isDelete);

    public Job insert(JobDTO jobDTO);

    public Job update(Long id, Job job);

    public boolean delete(Long id);

}
