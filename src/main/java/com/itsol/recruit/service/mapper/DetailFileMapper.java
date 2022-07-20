package com.itsol.recruit.service.mapper;

import com.itsol.recruit.dto.DetailFileDTO;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.JobRegisterRepository;
import com.itsol.recruit.repository.JobRepository;
import com.itsol.recruit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailFileMapper {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JobRegisterRepository jobRegister;
    @Autowired
    JobRepository jobRepository;
    public DetailFileDTO convertEntityToDTO(JobRegister jobRegister) {
        User user = userRepository.findUserById(jobRegister.getUser().getId());
        Job job = jobRepository.findJobById(jobRegister.getJob().getId());

        DetailFileDTO dto = new DetailFileDTO();
        dto.setId(jobRegister.getId());
        dto.setAddressWork(job.getAddressWork());
        dto.setNameJob(job.getName());
        dto.setJobRequirement(job.getJobRequirement());
        dto.setName(jobRegister.getUser().getName());
        dto.setStatus(jobRegister.getStatusJobRegister().getDescription());
        dto.setReason(jobRegister.getReason());
        dto.setNumberExperience(job.getNumberExperience());
        dto.setAddressWork(job.getAddressWork());
        dto.setQtyPerson(job.getQtyPerson());
        dto.setSkills(job.getSkills());
        dto.setDescription(job.getDescription());
        dto.setSalaryMax(job.getSalaryMax());
        dto.setSalaryMin(job.getSalaryMin());
        dto.setViews(job.getViews());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setHomeTown(user.getHomeTown());
        dto.setGender(user.getGender());
        return dto;
    }
}
