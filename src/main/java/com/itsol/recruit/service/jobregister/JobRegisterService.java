package com.itsol.recruit.service.jobregister;

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


    public Page<JobRegisterDTO> getAllJobRegister(int page, int pageSize, String sort, boolean type) {
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
        return jobRegister.findJobRegister(pageable).map(this::convertEntityToDTO);
    }

    public JobRegisterDTO convertEntityToDTO(JobRegister jobRegister) {
        JobRegisterDTO fileRecruitDTO = new JobRegisterDTO();
        fileRecruitDTO.setId(jobRegister.getId());
        fileRecruitDTO.setName(jobRegister.getUser().getName());
        fileRecruitDTO.setJobPosition(jobRegister.getJob().getJobPosition().getDescription());
        fileRecruitDTO.setDateRegister(jobRegister.getDateRegister());
        fileRecruitDTO.setCv(jobRegister.getCv());
        fileRecruitDTO.setStatus(jobRegister.getStatusJobRegister().getDescription());
        fileRecruitDTO.setReason(jobRegister.getReason());
        return fileRecruitDTO;
    }

}
