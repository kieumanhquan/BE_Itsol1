package com.itsol.recruit.service;

import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.entity.Job;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JobService {

    public Page<Job> getAllJob(int page, int pageSize, String sort, boolean type);

    public Job findById(Long id);

//    public Job findJobByName(String name);

    public List<Job> findJobByManyCon(String keyword);

    //Trung` job <=> name + create_id_je
    public boolean isExistedJob(String name, Long createId);

    public boolean isDeleteJob(boolean isDelete);

    public Job insert(JobDTO jobDTO);

    public Job update(Long id, Job job);

    public Job updateStatus(Long id, Long idStatus);

    public boolean delete(Long id);

}
