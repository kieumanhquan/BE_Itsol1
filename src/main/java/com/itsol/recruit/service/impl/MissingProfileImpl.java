package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.Profiles;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.*;
import com.itsol.recruit.web.vm.RegisterProfileVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MissingProfileImpl {
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

//    public Profiles addProfiles(String name,RegisterProfileVM registerProfileVM) {
//        User user = userRepository.findUserByUserName(name);
//        Profiles profiles = profilesRepository.findProfilesByUser(user);
//        user.setBirthDay(registerProfileVM.getBirthDay());
//        profiles.setDesiredWorkingForm(registerProfileVM.getDesiredWorkingForm());
//        profiles.setNumberYearsExperience(registerProfileVM.getNumberYearsExperience());
//
//    }

}
