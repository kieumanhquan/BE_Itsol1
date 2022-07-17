package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.StatusJobRegister;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.JobRegisterRepository;
import com.itsol.recruit.repository.StatusJobRegisterRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.BookInterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookInterviewServiceImpl {
    @Autowired
    JobRegisterRepository jobRegisterRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StatusJobRegisterRepository statusJobRegisterRepository;

    public String BookInter(Long id) {
        String success = "success";
        JobRegister jobRegister = jobRegisterRepository.findJobRegisterById(id);
        StatusJobRegister statusJobRegister = statusJobRegisterRepository.findStatusJobRegisterById(3l);
        jobRegister.setStatusJobRegister(statusJobRegister);
        jobRegisterRepository.save(jobRegister);
        return success;
    }
}
