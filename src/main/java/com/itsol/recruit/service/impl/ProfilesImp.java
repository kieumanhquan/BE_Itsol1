package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.Profiles;
import com.itsol.recruit.repository.ProfilesRepository;
import com.itsol.recruit.service.ProfilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfilesImp implements ProfilesService {
    @Autowired
    ProfilesRepository profilesRepository;


    @Override
    public Profiles findProfindByUser(Long id) {
        Profiles profiles = profilesRepository.findProfilesByUser(id);
        return profiles;
    }
}
