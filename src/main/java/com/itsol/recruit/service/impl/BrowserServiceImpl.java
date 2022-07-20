package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.StatusJobRegister;
import com.itsol.recruit.repository.JobRegisterRepository;
import com.itsol.recruit.repository.StatusJobRegisterRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.BrowserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrowserServiceImpl implements BrowserService {
    @Autowired
    private JobRegisterRepository jobRegisterRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private StatusJobRegisterRepository statusJobRegisterRepository;

    @Override
    public String browser(long id) {
        String fail = "fail";
        String success = "success";
        JobRegister jobRegister = jobRegisterRepository.findJobRegisterById(id);
        if (jobRegister.getStatusJobRegister().getId() == 4) {
            jobRegister.setStatusJobRegister(statusJobRegisterRepository.findStatusJobRegisterById(2l));
            jobRegisterRepository.save(jobRegister);
            return success;
        }
        return fail;

    }
}
