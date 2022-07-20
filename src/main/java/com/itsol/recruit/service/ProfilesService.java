package com.itsol.recruit.service;

import com.itsol.recruit.entity.Profiles;
import com.itsol.recruit.entity.User;

public interface ProfilesService {
    Profiles findProfindByUser(Long id);

}
