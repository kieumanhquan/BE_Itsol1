package com.itsol.recruit.service.impl;

import com.itsol.recruit.dto.DetailFileDTO;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.JobRegisterRepository;
import com.itsol.recruit.repository.JobRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.DetailFileService;
import com.itsol.recruit.service.mapper.DetailFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailFileServiceImpl implements DetailFileService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private JobRegisterRepository jobRegister;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private DetailFileMapper detailFileMapper;

    @Override
    public DetailFileDTO getDetailFile(long id) {
        return detailFileMapper.convertEntityToDTO(jobRegister.findJobRegisterById(id));
    }


}
