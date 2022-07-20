package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.JobRegisterRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.jobregister.SendJobRegisterService;
import com.itsol.recruit.web.vm.SendJobRegisVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendJobRegisterServiceImpl implements SendJobRegisterService {
    @Autowired
    JobRegisterRepository jobRegisterRepository;
    @Autowired
    private EmailServiceImpl emailServiceImpl;
    @Autowired
    UserRepository userRepository;

    @Override
    public String sendMailJobRegis(Long id, SendJobRegisVm sendJobRegisVm) {
        String success = "success";
        JobRegister jobRegister = jobRegisterRepository.findJobRegisterById(id);
        String email = emailServiceImpl.buildJobRegisterEmail(jobRegister.getUser().getName(), sendJobRegisVm);
        emailServiceImpl.sendEmailJobRegister(id, email);
        return success;
    }
}
