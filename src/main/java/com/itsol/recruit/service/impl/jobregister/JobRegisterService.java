package com.itsol.recruit.service.impl.jobregister;

import com.itsol.recruit.dto.JobRegisterDTO;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.JobRegisterRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.web.vm.PageVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobRegisterService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JobRegisterRepository jobRegister;

    public Page<JobRegisterDTO> getAllJobRegister(int pageNo, int pageSize, String sort) {
        Pageable pageable;
        if (sort == null) {
            pageable = PageRequest.of(pageNo, pageSize);
        } else {
            pageable = PageRequest.of(pageNo,pageSize,Sort.by(sort));
        }
        return  jobRegister.findJobRegister(pageable).map(this::convertEntityToDTO);
    }


    public JobRegisterDTO convertEntityToDTO(JobRegister jobRegister) {
        JobRegisterDTO fileRecruitDTO = new JobRegisterDTO();
        fileRecruitDTO.setName(jobRegister.getUser().getName());
        fileRecruitDTO.setJobPosition(jobRegister.getJob().getJobPosition().getDescription());
        fileRecruitDTO.setDateRegister(jobRegister.getDateRegister());
        fileRecruitDTO.setCv(jobRegister.getCv());
        fileRecruitDTO.setStatus(jobRegister.getStatusJobRegister().getDescription());
        fileRecruitDTO.setReason(jobRegister.getReason());
        return fileRecruitDTO;
    }
}
