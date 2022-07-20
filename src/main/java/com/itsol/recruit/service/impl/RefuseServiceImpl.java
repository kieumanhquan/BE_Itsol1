package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.StatusJobRegister;
import com.itsol.recruit.repository.JobRegisterRepository;
import com.itsol.recruit.repository.StatusJobRegisterRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.RefuseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefuseServiceImpl implements RefuseService {
    @Autowired
    private JobRegisterRepository jobRegisterRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private StatusJobRegisterRepository statusJobRegisterRepository;

    @Override
    public String refuse(long id, String reason) {
        String fail = "fail";
        String success = "success";
        if (reason == null) {
            return fail;
        }
        JobRegister jobRegister = jobRegisterRepository.findJobRegisterById(id);
        if (jobRegister.getStatusJobRegister().getId() == 4 || jobRegister.getStatusJobRegister().getId() == 2 || jobRegister.getStatusJobRegister().getId() == 3) {
            jobRegister.setStatusJobRegister(statusJobRegisterRepository.findStatusJobRegisterById(1l));
            jobRegister.setReason(reason);
            jobRegisterRepository.save(jobRegister);
            return success;
        } else {
            return fail;
        }
    }
}
