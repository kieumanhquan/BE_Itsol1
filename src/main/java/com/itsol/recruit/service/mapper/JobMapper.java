package com.itsol.recruit.service.mapper;

import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobMapper implements EntityMapper<JobDTO, Job>{

    @Autowired
    JobPositionRepository jobPositionRepository;
    @Autowired
    WorkingFormRepository workingFormRepository;
    @Autowired
    AcademicLevelRepository academicLevelRepository;
    @Autowired
    RankRepository rankRepository;
    @Autowired
    UserRepository contactUserRepository;
    @Autowired
    UserRepository createUserRepository;
    @Autowired
    UserRepository updateUserRepository;
    @Autowired
    StatusJobRepository statusJobRepository;


    @Override
    public Job toEntity(JobDTO dto) {

        if (dto == null) {
            return null;
        }

        Job entity = new Job();
        BeanUtils.copyProperties(dto, entity);

//        entity.setId(dto.getId());
//        entity.setName(dto.getName());
        entity.setJobPosition(jobPositionRepository.findById(dto.getJobPositionId()).get());
//        entity.setNumberExperience(dto.getNumberExperience());
        entity.setWorkingForm(workingFormRepository.findById(dto.getWorkingFormId()).get());
//        entity.setAddressWork(dto.getAddressWork());
        entity.setAcademicLevel(academicLevelRepository.findById(dto.getAcademicLevelId()).get());
        entity.setRank(rankRepository.findById(dto.getRankId()).get());
//        entity.setQtyPerson(dto.getQtyPerson());
//        entity.setStartRecruitmentDate(dto.getStartRecruitmentDate());
//        entity.setDueDate(dto.getDueDate());
//        entity.setSkills(dto.getSkills());
//        entity.setDescription(dto.getDescription());
//        entity.setJobRequirement(dto.getJobRequirement());
//        entity.setSalaryMax(dto.getSalaryMax());
//        entity.setSalaryMin(dto.getSalaryMin());
        entity.setContact(contactUserRepository.findById(dto.getContactId()).get());
        entity.setCreate(createUserRepository.findById(dto.getCreateId()).get());
//        entity.setCreateDate(dto.getCreateDate());
        entity.setUpdate(updateUserRepository.findById(dto.getUpdateId()).get());
//        entity.setUpdateDate(dto.getUpdateDate());
        entity.setStatus(statusJobRepository.findById(dto.getStatusId()).get());
//        entity.setViews(dto.getViews());
        entity.setDelete(false);

        return entity;
    }

    @Override
    public JobDTO toDto(Job entity) {
        if (entity == null) {
            return null;
        }

        JobDTO dto = new JobDTO();
        BeanUtils.copyProperties(entity, dto);

/*        dto.setName(entity.getName());
        dto.setJobPositionId(entity.getJobPosition().getId());
        dto.setNumberExperience(entity.getNumberExperience());
        dto.setWorkingFormId(entity.getWorkingForm().getId());
        dto.setAddressWork(entity.getAddressWork());
        dto.setAcademicLevelId(entity.getAcademicLevel().getId());
        dto.setRankId(entity.getRank().getId());
        dto.setQtyPerson(entity.getQtyPerson());
        dto.setStartRecruitmentDate(entity.getStartRecruitmentDate());
        dto.setDueDate(entity.getDueDate());
        dto.setSkills(entity.getSkills());
        dto.setDescription(entity.getDescription());
        dto.setJobRequirement(entity.getJobRequirement());
        dto.setSalaryMax(entity.getSalaryMax());
        dto.setSalaryMin(entity.getSalaryMin());
        dto.setContactId(entity.getContact().getId());
        dto.setCreateId(entity.getCreate().getId());
        dto.setCreateDate(entity.getCreateDate());
        dto.setUpdateId(entity.getUpdate().getId());
        dto.setUpdateDate(entity.getUpdateDate());
        dto.setStatusId(entity.getStatus().getId());
        dto.setViews(entity.getViews());*/

        return dto;
    }


    @Override
    public List<Job> toEntity(List<JobDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<JobDTO> toDto(List<Job> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
