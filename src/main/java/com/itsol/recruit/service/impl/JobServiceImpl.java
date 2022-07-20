package com.itsol.recruit.service.impl;

import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.repository.JobRepository;
import com.itsol.recruit.repository.StatusJobRepository;
import com.itsol.recruit.service.JobService;
import com.itsol.recruit.service.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

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
    public Page<Job> getAllJob(int page, int pageSize, String sort, boolean type) {
        Pageable pageable;
        if (sort == null) {
            pageable = PageRequest.of(page, pageSize);
        } else {
            if (type) {
                pageable = PageRequest.of(page, pageSize, Sort.by(sort).ascending());
            } else {
                pageable = PageRequest.of(page, pageSize, Sort.by(sort).descending());
            }
        }
        return jobRepository.findJobPage(pageable);
    }


    @Override
    public Job findById(Long id) {
        System.out.println("id " + id);
        return jobRepository.findJobById(id);
    }

//    @Override
//    public Job findJobByName(String name) {
//        return jobRepository.findJobByName(name);
//    }

    @Override
    public List<Job> findJobByManyCon(String keyword) {
        return jobRepository.findJobByManyCon(keyword);
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

//        if(jobDTO.getId() == null) {
            Job job = jobMapper.toEntity(jobDTO);
            return jobRepository.save(job);
//        }
//        else {
//            Job jobOld = jobRepository.findById(jobDTO.getId()).get();
//            Job job = jobMapper.toEntity(jobDTO);
//
//        }
    }

    @Override
    public Job update(Long id, Job jobUpdate) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Không tồn tại công việc này với id: " + id));

        job.setName(jobUpdate.getName());
        job.setJobPosition(jobUpdate.getJobPosition());
        job.setNumberExperience(jobUpdate.getNumberExperience());
        job.setWorkingForm(jobUpdate.getWorkingForm());
        job.setAddressWork(jobUpdate.getAddressWork());
        job.setAcademicLevel(jobUpdate.getAcademicLevel());
        job.setRank(jobUpdate.getRank());
        job.setQtyPerson(jobUpdate.getQtyPerson());
        job.setStartRecruitmentDate(jobUpdate.getStartRecruitmentDate());
        job.setDueDate(jobUpdate.getDueDate());
        job.setSkills(jobUpdate.getSkills());
        job.setDescription(jobUpdate.getDescription());
        job.setInterest(jobUpdate.getInterest());
        job.setJobRequirement(jobUpdate.getJobRequirement());
        job.setSalaryMax(jobUpdate.getSalaryMax());
        job.setSalaryMin(jobUpdate.getSalaryMin());
        job.setContact(jobUpdate.getContact());
        job.setCreate(jobUpdate.getCreate());
        job.setCreateDate(jobUpdate.getCreateDate());
        job.setUpdate(jobUpdate.getUpdate());
        job.setUpdateDate(jobUpdate.getUpdateDate());
        job.setStatus(jobUpdate.getStatus());
        job.setViews(jobUpdate.getViews());
        job.setDelete(false);

        return jobRepository.save(job);

    }

    @Autowired
    StatusJobRepository statusJobRepository;

    public Job updateStatus(Long id, Long idStatus) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Không tồn tại công việc này với id: " + id));
        job.setStatus(statusJobRepository.findById(idStatus).get());
        return jobRepository.save(job);
    }

    @Override
    public boolean delete(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Không tồn tại công việc này với id: " + id));
        jobRepository.delete(job);
        return true;
    }
}
