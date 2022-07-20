package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.repository.JobRegisterRepository;
import com.itsol.recruit.repository.StatusJobRegisterRepository;
import com.itsol.recruit.service.SuccessfullService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SuccessfullServiceImpl implements SuccessfullService {
    @Autowired
    JobRegisterRepository jobRegisterRepository;
    @Autowired
    StatusJobRegisterRepository statusJobRegisterRepository;

    @Override
    public String successfull(long id) {
        String success = "success";
        JobRegister jobRegister = jobRegisterRepository.findJobRegisterById(id);
        jobRegister.setStatusJobRegister(statusJobRegisterRepository.findStatusJobRegisterById(5l));
        jobRegisterRepository.save(jobRegister);
        return success;
    }
}
