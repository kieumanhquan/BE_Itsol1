package com.itsol.recruit.service.impl.jobregister;

import com.itsol.recruit.dto.JobRegisterDTO;
import com.itsol.recruit.entity.*;
import com.itsol.recruit.repository.*;
import com.itsol.recruit.service.impl.FileStorageServiceImpl;
import com.itsol.recruit.web.vm.PageVM;
import com.itsol.recruit.web.vm.RegisterProfileVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobRegisterService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JobRegisterRepository jobRegisterRepository;
    @Autowired
    FileStorageServiceImpl fileStorageService;
    @Autowired
    StatusJobRegisterRepository statusJobRegister;
    @Autowired
    FileDBRepository fileDBRepository;
    @Autowired
    ProfilesRepository profilesRepository;
    @Autowired
    JobRepository jobRepository;

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
        return jobRegisterRepository.findJobRegister(pageable).map(this::convertEntityToDTO);
    }

    public int totalRecord() {
        return jobRegisterRepository.findAll().size();
    }

    public JobRegisterDTO convertEntityToDTO(JobRegister jobRegister) {
        JobRegisterDTO fileRecruitDTO = new JobRegisterDTO();
        fileRecruitDTO.setId(jobRegister.getId());
        fileRecruitDTO.setName(jobRegister.getUser().getName());
        fileRecruitDTO.setJobPosition(jobRegister.getJob().getJobPosition().getDescription());
        fileRecruitDTO.setDateRegister(jobRegister.getDateRegister());
        fileRecruitDTO.setCv(jobRegister.getFileDB().getId());
        fileRecruitDTO.setStatus(jobRegister.getStatusJobRegister().getDescription());
        fileRecruitDTO.setReason(jobRegister.getReason());
        return fileRecruitDTO;
    }

    public String jobRegistration(String userName, MultipartFile file) throws IOException {
        String success = "success";
        FileDB fileDB = fileStorageService.store(file);
        User user = userRepository.findUserByUserName(userName);
        JobRegister jobRegister = new JobRegister();
        jobRegister.setStatusJobRegister(statusJobRegister.findStatusJobRegisterById(4l));
        jobRegister.setUser(user);
        Date date = new java.util.Date();
        jobRegister.setDateRegister(date);
        jobRegister.setFileDB(fileDBRepository.findFileDBById(fileDB.getId()));
        jobRegister.setJob(jobRepository.findJobById(1l));
        jobRegisterRepository.save(jobRegister);
        return success;
    }
}

